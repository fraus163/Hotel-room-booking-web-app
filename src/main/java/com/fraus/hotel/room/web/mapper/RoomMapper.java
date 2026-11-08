package com.fraus.hotel.room.web.mapper;

import com.fraus.hotel.room.domain.db.RoomEntity;
import com.fraus.hotel.room.web.dto.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TagMapper.class})
public interface RoomMapper {
    RoomDto toRoomDto(RoomEntity roomEntity);
}