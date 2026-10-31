package com.fraus.hotel.auth.web.mapper;

import com.fraus.hotel.auth.domain.db.UserEntity;
import com.fraus.hotel.auth.web.dto.UserDto;
import com.fraus.hotel.reservation.web.mapper.ReservationMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ReservationMapper.class})
public interface UserMapper {
    UserEntity toEntity(UserDto userDto);

    @AfterMapping
    default void linkReservations(@MappingTarget UserEntity userEntity) {
        userEntity.getReservations().forEach(reservation -> reservation.setUser(userEntity));
    }

    UserDto toUserDto(UserEntity userEntity);
}