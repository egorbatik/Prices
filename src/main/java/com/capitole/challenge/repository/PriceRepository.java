package com.capitole.challenge.repository;

import com.capitole.challenge.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "SELECT * FROM prices WHERE brand_id = ?3 AND ?1 BETWEEN  start_date AND end_date AND product_id= ?2 ORDER BY priority DESC LIMIT 1", nativeQuery = true)
    Price findByBrandIdAndApplicationDateAndProductOrderByPriority( LocalDateTime applicationDate, Long productId,Long brandId);
}