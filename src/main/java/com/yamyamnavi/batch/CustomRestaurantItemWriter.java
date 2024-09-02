package com.yamyamnavi.batch;

import com.yamyamnavi.storage.restaurant.RestaurantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomRestaurantItemWriter implements ItemWriter<RestaurantEntity> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void write(Chunk<? extends RestaurantEntity> chunk) throws Exception {
        String sql = "INSERT INTO restaurant (name, jibeon_address, road_address, location, category, is_business_active, unique_name, telephone, score, created_at, updated_at) " +
                "VALUES (?, ?, ?, ST_GeomFromText(?, 4326), ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE is_business_active = VALUES(is_business_active), updated_at = VALUES(updated_at)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                RestaurantEntity restaurant = chunk.getItems().get(i);
                LocalDateTime now = LocalDateTime.now();

                ps.setString(1, restaurant.getName());
                ps.setString(2, restaurant.getJibeonAddress());
                ps.setString(3, restaurant.getRoadAddress());
                ps.setString(4, String.format("POINT(%f %f)", restaurant.getLocation().getY(), restaurant.getLocation().getX()));
                ps.setString(5, restaurant.getCategory());
                ps.setBoolean(6, restaurant.getIsBusinessActive());
                ps.setString(7, restaurant.getUniqueName());
                ps.setString(8, restaurant.getTelephone());
                ps.setDouble(9, 0.0);
                ps.setTimestamp(10, Timestamp.valueOf(now));
                ps.setTimestamp(11, Timestamp.valueOf(now));
            }

            @Override
            public int getBatchSize() {
                return chunk.getItems().size();
            }
        });
    }
}