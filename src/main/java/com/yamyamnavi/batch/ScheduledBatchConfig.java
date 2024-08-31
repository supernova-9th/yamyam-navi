package com.yamyamnavi.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledBatchConfig {

    private final JobLauncher jobLauncher;
    private final Job importSeoulFacilitiesJob;

    public ScheduledBatchConfig(JobLauncher jobLauncher, @Qualifier("importSeoulFacilitiesJob") Job importSeoulFacilitiesJob) {
        this.jobLauncher = jobLauncher;
        this.importSeoulFacilitiesJob = importSeoulFacilitiesJob;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduleSeoulFacilityImport() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(importSeoulFacilitiesJob, jobParameters);
    }
}