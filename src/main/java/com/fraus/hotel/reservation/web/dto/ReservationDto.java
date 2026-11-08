package com.fraus.hotel.reservation.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDto {
    private Long roomId;
    private Integer days;
    private LocalDate arrivalDate;
}
