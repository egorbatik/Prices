package com.capitole.challenge.controller;

import com.capitole.challenge.dto.PriceRequestDTO;
import com.capitole.challenge.dto.PriceResponseDTO;
import com.capitole.challenge.entity.Price;
import com.capitole.challenge.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

@RestController
public class PriceController {
    private Logger logger = LoggerFactory.getLogger(PriceController.class);
    private final PriceService priceService;

    PriceController(@Autowired PriceService priceService){
        this.priceService= priceService;
    }

    @PostMapping("/price")
    public ResponseEntity<PriceResponseDTO> getPrice(@RequestBody PriceRequestDTO priceRequestDTO){
       try {
           Price price = priceService.getPriceForDate(LocalDateTime.parse(priceRequestDTO.getDateTime()), priceRequestDTO.getProductId(), priceRequestDTO.getBrandId());
           if (price == null) {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
           return new ResponseEntity<PriceResponseDTO>(PriceResponseDTO.fromEntity(price), HttpStatus.OK);
       }
       catch (Exception ex) {
           logger.error("Failed:", ex);
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

}
