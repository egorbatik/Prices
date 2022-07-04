package com.capitole.challenge.service;

import com.capitole.challenge.entity.Price;

import java.time.LocalDateTime;

public interface PriceService {
    Price getPriceForDate(LocalDateTime dateTime, Long productId, Long brandId);
}
