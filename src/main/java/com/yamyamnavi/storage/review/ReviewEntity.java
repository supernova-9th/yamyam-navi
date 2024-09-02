package com.yamyamnavi.storage.review;

import com.yamyamnavi.storage.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "review")
public class ReviewEntity extends BaseEntity {

    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false, updatable = false)
    private Long userId;
    @Column(nullable = false, updatable = false)
    private Long restaurantId;

    @Builder
    private ReviewEntity(String content, Integer score, Long userId, Long restaurantId) {
        this.content = content;
        this.score = score;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

}
