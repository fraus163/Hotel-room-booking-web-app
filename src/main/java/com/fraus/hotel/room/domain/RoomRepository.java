package com.fraus.hotel.room.domain;

import com.fraus.hotel.room.domain.db.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
