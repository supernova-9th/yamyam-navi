package com.yamyamnavi.batch;

import com.yamyamnavi.client.SeoulOpenApiClient;
import com.yamyamnavi.client.response.SeoulApiResponse;
import com.yamyamnavi.client.response.SeoulFacility;
import com.yamyamnavi.storage.restaurant.RestaurantEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Point;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SeoulFacilityBatchConfig {

    private final SeoulOpenApiClient apiClient;
    private final CoordinateConverter coordinateConverter;
    private final CustomRestaurantItemWriter customRestaurantItemWriter;

    @Value("${seoul.openapi.key}")
    private String apiKey;

    @Bean
    @Qualifier("importSeoulFacilitiesJob")
    public Job importSeoulFacilitiesJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("importSeoulFacilitiesJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     ItemReader<SeoulFacility> reader,
                     ItemProcessor<SeoulFacility, RestaurantEntity> processor
    ) {
        return new StepBuilder("step", jobRepository)
                .<SeoulFacility, RestaurantEntity>chunk(1000, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(customRestaurantItemWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10)
                .build();
    }

    @Bean
    public ItemReader<SeoulFacility> reader() {
        return new ItemReader<>() {
            private final AtomicInteger nextStartIndex = new AtomicInteger(1);
            private List<SeoulFacility> currentBatch = null;
            private int currentIndex = 520000;

            @Override
            public SeoulFacility read() throws Exception {
                if (currentBatch == null || currentIndex >= currentBatch.size()) {
                    currentBatch = fetchNextBatch();
                    currentIndex = 0;
                    if (currentBatch == null || currentBatch.isEmpty()) {
                        log.info("No more data to process. Total processed: {}. currentIndex: {}", nextStartIndex.get() - 1, currentIndex);
                        return null;
                    }
                }
                return currentBatch.get(currentIndex++);
            }

            private List<SeoulFacility> fetchNextBatch() {
                int startIndex = nextStartIndex.getAndAdd(1000);
                int endIndex = startIndex + 999;
                SeoulApiResponse response = apiClient.fetchData(apiKey, startIndex, endIndex);

                if (response.isNoDataResponse()) {
                    log.info("Received INFO-200 code. No more data available.");
                    return null;
                }

                return response.localData072404().row();
            }
        };
    }

    @Bean
    public ItemProcessor<SeoulFacility, RestaurantEntity> processor() {
        return item -> {
            if (ObjectUtils.isEmpty(item.x()) || ObjectUtils.isEmpty(item.y())) {
                return null;
            }

            try {
                Point location = coordinateConverter.convert(
                        Double.parseDouble(item.x()),
                        Double.parseDouble(item.y())
                );

                return RestaurantEntity.builder()
                        .name(item.name())
                        .jibeonAddress(item.jibeonAddress())
                        .roadAddress(item.roadAddress())
                        .location(location)
                        .category(item.category())
                        .isBusinessActive(item.isActive())
                        .uniqueName(item.getUniqueName())
                        .telephone(item.telephone())
                        .build();
            } catch (Exception e) {
                log.error("Error processing item: {}", item, e);
                return null;
            }
        };
    }
}