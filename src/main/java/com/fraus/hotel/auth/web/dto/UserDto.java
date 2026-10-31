package com.fraus.hotel.auth.web.dto;

import com.fraus.hotel.reservation.web.dto.ReservationResponseDto;

import java.util.Set;

/**
 * DTO for {@link com.fraus.hotel.auth.domain.db.UserEntity}
 */
public record UserDto(
        String email,
        String username,
        Set<ReservationResponseDto> reservations
) {
}