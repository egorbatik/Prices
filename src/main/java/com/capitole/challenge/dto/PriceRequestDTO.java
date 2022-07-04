package com.capitole.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class PriceRequestDTO {
    String dateTime;
    Long productId;
    Long brandId;
}
