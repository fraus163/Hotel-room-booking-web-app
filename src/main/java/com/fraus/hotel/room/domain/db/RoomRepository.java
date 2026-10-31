package com.fraus.hotel.room.domain.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    List<RoomEntity> findByEnabled(Boolean enabled);
}
