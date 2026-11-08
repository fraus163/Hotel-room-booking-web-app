package com.fraus.hotel.reservation.domain;

import com.fraus.hotel.auth.domain.db.UserRepository;
import com.fraus.hotel.exception.RoomAlreadyReservedException;
import com.fraus.hotel.reservation.domain.db.ReservationEntity;
import com.fraus.hotel.reservation.domain.db.ReservationRepository;
import com.fraus.hotel.reservation.domain.db.StatusType;
import com.fraus.hotel.reservation.web.dto.ReservationDto;
import com.fraus.hotel.room.domain.db.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    public void reserveRoom(ReservationDto reservationDto, UserDetails user) {
        var foundedRoom = roomRepository.findById(reservationDto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Номер не найден: roomId=" + reservationDto.getRoomId()));
        if (!Boolean.TRUE.equals(foundedRoom.getEnabled())) {
            throw new RoomAlreadyReservedException("Номер уже забронирован или недоступен");
        }
        var foundedUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: username=" + user.getUsername()));
        foundedRoom.setEnabled(false);
        var reservationToSave = ReservationEntity.builder()
                .status(StatusType.STATUS_PENDING)
                .arrivalDate(reservationDto.getArrivalDate())
                .fullPrice(foundedRoom.getPricePerNight().multiply(BigDecimal.valueOf(reservationDto.getDays())))
                .createdAt(Instant.now())
                .days(reservationDto.getDays())
                .room(foundedRoom)
                .user(foundedUser)
                .build();
        reservationRepository.save(reservationToSave);
    }

    @Transactional
    public void cancelReservation(Long reservationId, UserDetails user) {
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Бронирование не найдено: id=" + reservationId));

        if (!reservation.getUser().getUsername().equals(user.getUsername())) {
            throw new AccessDeniedException("Вы не можете отменить чужое бронирование");
        }

        if (reservation.getStatus() == StatusType.STATUS_CANCELLED) {
            throw new IllegalStateException("Бронирование уже отменено");
        }

        reservation.setStatus(StatusType.STATUS_CANCELLED);
        reservation.getRoom().setEnabled(true);
    }
}
