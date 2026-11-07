package com.fraus.hotel.room.domain;

import com.fraus.hotel.room.domain.db.RoomEntity;
import com.fraus.hotel.room.domain.db.RoomRepository;
import com.fraus.hotel.room.web.dto.RoomDto;
import com.fraus.hotel.room.web.dto.RoomFilterDto;
import com.fraus.hotel.room.web.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public Page<RoomEntity> getEnabledRooms(Pageable pageable, RoomFilterDto roomFilterDto) {
        return roomRepository.findByFilterAndEnabled(
                pageable,
                roomFilterDto.getName(),
                roomFilterDto.getPriceFrom(),
                roomFilterDto.getPriceTo(),
                roomFilterDto.getArea(),
                roomFilterDto.getPeople(),
                true
        );
    }

    public RoomDto getRoomById(Long id) {
        RoomEntity foundedRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found: id=" + id));
        return roomMapper.toRoomDto(foundedRoom);
    }
}
