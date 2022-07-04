package com.capitole.challenge.service;

import com.capitole.challenge.entity.Price;
import com.capitole.challenge.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceServiceImpl implements PriceService {

    private PriceRepository priceRepository;

    PriceServiceImpl(@Autowired PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPriceForDate(LocalDateTime dateTime, Long productId, Long brandId) {
        return priceRepository.findByBrandIdAndApplicationDateAndProductOrderByPriority(dateTime,productId,brandId);
    }
}
