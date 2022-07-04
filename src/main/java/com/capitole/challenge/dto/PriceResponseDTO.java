package com.capitole.challenge.dto;

import com.capitole.challenge.entity.Brand;
import com.capitole.challenge.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponseDTO {

    private Brand brand;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer priceListId;

    private Long productId;

    private Double price;

    private String currency;

    public static PriceResponseDTO fromEntity(Price price){
        return new PriceResponseDTO(price.getBrand(), price.getStartDate(), price.getEndDate(), price.getPriceListId(), price.getProductId(), price.getPrice(), price.getCurrency());
    }
}

