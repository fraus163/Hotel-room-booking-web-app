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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public Page<RoomDto> getRoomsByFilter(Pageable pageable, RoomFilterDto roomFilterDto) {
        var foundedRooms = roomRepository.findRoomsByFilter(
                pageable,
                roomFilterDto.getName(),
                roomFilterDto.getPriceFrom(),
                roomFilterDto.getPriceTo(),
                roomFilterDto.getArea(),
                roomFilterDto.getPeople(),
                true
        );
        return foundedRooms.map(roomMapper::toRoomDto);
    }

    public RoomDto getRoomById(Long id) {
        var foundedRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found: id=" + id));
        return roomMapper.toRoomDto(foundedRoom);
    }
}
