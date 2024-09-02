package com.yamyamnavi.support.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class PageResponse<T> {

    private int currentPage;
    private int lastPage;
    private int limit;
    private long total;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> contents;

    private PageResponse(int page, int lastPage, int limit, long total, List<T> contents) {
        this.currentPage = page;
        this.lastPage = lastPage;
        this.limit = limit;
        this.total = total;
        this.contents = contents;
    }


    public static <T> PageResponse<T> of(int page, int limit, long total, List<T> contents) {
        return new PageResponse<>(calculateCurrentPage(page), calculateMaxPage(total, limit), limit, total, contents);
    }

    public static <T> PageResponse<T> of(Page<T> page) {
        int limit = page.getSize();
        return new PageResponse<>(calculateCurrentPage(page.getNumber()), calculateMaxPage(page.getTotalElements(), limit), limit, page.getTotalElements(), page.getContent());
    }

    public static <T> PageResponse<T> of(Page<T> page, List<T> contents) {
        int limit = page.getSize();
        return new PageResponse<>(calculateCurrentPage(page.getNumber()), calculateMaxPage(page.getTotalElements(), limit), limit, page.getTotalElements(), contents);
    }

    /**
     * 현재 페이지 번호를 계산하는 메서드
     */
    private static int calculateCurrentPage(int page) {
        return page + 1;
    }

    /**
     * 최대 페이지 수를 계산하는 메서드
     */
    private static int calculateMaxPage(long total, int limit) {
        return (int) Math.ceil((double) total / limit);
    }
}
