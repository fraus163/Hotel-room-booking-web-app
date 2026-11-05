package com.fraus.hotel.reservation.domain.db;

import com.fraus.hotel.auth.domain.db.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    Page<ReservationEntity> findByUser(UserEntity user, Pageable pageable);
}