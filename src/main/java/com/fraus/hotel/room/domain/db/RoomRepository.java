package com.fraus.hotel.room.domain.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    Page<RoomEntity> findByEnabled(Pageable pageable, Boolean enabled);
}
