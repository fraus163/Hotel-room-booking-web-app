package com.fraus.hotel.room.domain;

import com.fraus.hotel.room.domain.db.RoomEntity;
import com.fraus.hotel.room.domain.db.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomEntity> getEnabledRooms() {
        return roomRepository.findByEnabled(true);
    }
}
