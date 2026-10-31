package com.fraus.hotel.reservation.web.mapper;

import com.fraus.hotel.reservation.domain.db.ReservationEntity;
import com.fraus.hotel.reservation.web.dto.ReservationResponseDto;
import com.fraus.hotel.room.web.mapper.RoomMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {RoomMapper.class})
public interface ReservationMapper {
    ReservationEntity toEntity(ReservationResponseDto reservationResponseDto);

    ReservationResponseDto toReservationResponseDto(ReservationEntity reservationEntity);
}