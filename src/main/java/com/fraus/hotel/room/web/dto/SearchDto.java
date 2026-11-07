package com.fraus.hotel.room.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    private String name;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private Integer area;
    private Integer people;
}
