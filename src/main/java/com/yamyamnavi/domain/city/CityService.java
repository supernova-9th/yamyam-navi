package com.yamyamnavi.domain.city;

import com.yamyamnavi.api.v1.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityFinder cityFinder;
    private final CityAppender cityAppender;

    /**
     * 도시 목록을 조회합니다.
     *
     * @return map key - 도/시
     *             value 시/군/구 리스트
     */
    @Transactional(readOnly = true)
    public List<CityResponse> getCites() {
        return cityFinder.findAll();
    }

    /**
     * CSV 파일을 업로드합니다.
     * @param file  CSV 파일 입니다.
     * @throws Exception
     */
    @Transactional
    public void upload(MultipartFile file) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : csvParser) {
                String dosi = record.get(0);
                String sgg = record.get(1);
                double lon = Double.parseDouble(record.get(2));
                double lat = Double.parseDouble(record.get(3));

                cityAppender.append(dosi, sgg, lon, lat);
            }
        }
    }
}
