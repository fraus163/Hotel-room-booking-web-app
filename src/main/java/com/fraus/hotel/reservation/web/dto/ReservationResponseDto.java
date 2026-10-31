package com.fraus.hotel.reservation.web.dto;

import com.fraus.hotel.reservation.domain.db.ReservationEntity;
import com.fraus.hotel.reservation.domain.db.StatusType;
import com.fraus.hotel.room.web.dto.RoomDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link ReservationEntity}
 */
public record ReservationResponseDto(
        StatusType status,
        LocalDate arrivalDate,
        BigDecimal fullPrice,
        Instant createdAt,
        Integer days, RoomDto room
) {
}