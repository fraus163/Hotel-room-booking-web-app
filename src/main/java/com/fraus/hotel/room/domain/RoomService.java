package com.fraus.hotel.room.domain;

import com.fraus.hotel.room.domain.db.RoomEntity;
import com.fraus.hotel.room.domain.db.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Page<RoomEntity> getEnabledRooms(Pageable pageable) {
        return roomRepository.findByEnabled(pageable, true);
    }
}
