package com.fraus.hotel.reservation.domain;

import com.fraus.hotel.auth.domain.db.UserRepository;
import com.fraus.hotel.reservation.domain.db.ReservationEntity;
import com.fraus.hotel.reservation.domain.db.ReservationRepository;
import com.fraus.hotel.reservation.domain.db.StatusType;
import com.fraus.hotel.reservation.web.dto.ReservationDto;
import com.fraus.hotel.room.domain.db.RoomEntity;
import com.fraus.hotel.room.domain.db.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public void reserveRoom(ReservationDto reservationDto, UserDetails user) {
        var room = roomRepository.findById(reservationDto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Номер не найден: roomId=" + reservationDto.getRoomId()));
        room.setEnabled(false);
        var username = user.getUsername();
        var foundedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: username=" + username));

        var reservationToSave = new ReservationEntity(
                null,
                StatusType.STATUS_PENDING,
                LocalDate.now(),
                new BigDecimal(room.getPricePerNight().doubleValue() * reservationDto.getDays()),
                Instant.now(),
                reservationDto.getDays(),
                room,
                foundedUser
        );
        reservationRepository.save(reservationToSave);
    }
}
