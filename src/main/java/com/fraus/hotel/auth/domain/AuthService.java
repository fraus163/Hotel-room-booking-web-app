package com.fraus.hotel.auth.domain;

import com.fraus.hotel.auth.domain.db.RoleType;
import com.fraus.hotel.auth.domain.db.UserEntity;
import com.fraus.hotel.auth.domain.db.UserRepository;
import com.fraus.hotel.auth.web.dto.RegisterDto;
import com.fraus.hotel.auth.web.dto.UserDto;
import com.fraus.hotel.reservation.domain.db.ReservationEntity;
import com.fraus.hotel.reservation.domain.db.ReservationRepository;
import com.fraus.hotel.reservation.web.dto.ReservationResponseDto;
import com.fraus.hotel.reservation.web.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;

    public void registerUser(RegisterDto registerDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(RoleType.ROLE_USER);
        user.setEnabled(true);

        userRepository.save(user);
    }

    public UserDto findUserByUsername(UserDetails user, Pageable pageable) {
        var username = user.getUsername();
        var foundedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: username=" + username));
        Page<ReservationEntity> reservations = reservationRepository.findByUser(foundedUser, pageable);
        Page<ReservationResponseDto> responseDtos = reservations.map(reservationMapper::toReservationResponseDto);
        return new UserDto(
                foundedUser.getUsername(),
                foundedUser.getEmail(),
                responseDtos
        );
    }
}
