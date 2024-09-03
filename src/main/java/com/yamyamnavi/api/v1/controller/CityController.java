package com.yamyamnavi.api.v1.controller;

import com.yamyamnavi.api.v1.response.CityResponse;
import com.yamyamnavi.domain.city.CityService;
import com.yamyamnavi.support.error.FileUploadException;
import com.yamyamnavi.support.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cities")
@Tag(name = "City API", description = "도시 정보를 조회하는 API")
public class CityController {

    private final CityService cityService;

    /**
     * 도시 목록을 조회합니다.
     *
     * @return 도시 목록 정보가 담긴 CityResponse 객체 리스트
     */
    @GetMapping
    @Operation(summary = "도시 목록 조회", description = "도시 및 시/군/구의 리스트를 조회합니다.")
    public ResultResponse<List<CityResponse>> getCites() {
        return new ResultResponse<>(cityService.getCites());
    }

    /**
     * CSV 파일을 업로드합니다.
     *
     * @param file CSV 파일 입니다.
     * @return 파일이 성공적으로 처리되었음을 나타내는 일반적인 성공 응답입니다.
     * @throws FileUploadException 파일 업로드 또는 처리 중 오류가 발생할 경우 던져집니다.
     */
    @PostMapping("/upload")
    public ResultResponse<Void> upload(@RequestParam("file") MultipartFile file) {
        try {
            cityService.upload(file);
            return new ResultResponse<>();
        } catch (Exception e) {
            throw new FileUploadException();
        }
    }
}
