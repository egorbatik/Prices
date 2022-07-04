package com.capitole.challenge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Getter
@Setter
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinTable(name = "brands")
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name= "start_date")
    private LocalDateTime startDate;

    @Column(name= "end_date")
    private LocalDateTime endDate;

    @Column(name= "price_list")
    private Integer priceListId;

    @Column(name= "product_id")
    private Long productId;

    private Integer priority;

    private Double price;

    @Column(name = "curr")
    private String currency;

}