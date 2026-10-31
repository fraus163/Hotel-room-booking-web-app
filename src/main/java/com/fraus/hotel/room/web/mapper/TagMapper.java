package com.fraus.hotel.room.web.mapper;

import com.fraus.hotel.room.domain.db.TagEntity;
import com.fraus.hotel.room.web.dto.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TagMapper {
    TagEntity toEntity(TagDto tagDto);

    TagDto toTagDto(TagEntity tagEntity);
}