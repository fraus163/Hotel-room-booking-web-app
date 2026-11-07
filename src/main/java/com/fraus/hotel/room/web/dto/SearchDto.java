package com.fraus.hotel.room.web.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchDto {
    private String name;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private Integer area;
    private Integer people;
}
