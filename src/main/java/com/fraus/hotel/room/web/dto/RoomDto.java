package com.fraus.hotel.room.web.dto;

import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link com.fraus.hotel.room.domain.db.RoomEntity}
 */
public record RoomDto(
        Set<TagDto> tags,
        BigDecimal pricePerNight,
        Integer area,
        Integer people,
        String description,
        String name
) {
}