package com.fraus.hotel.auth.domain;

import com.fraus.hotel.auth.domain.db.RoleType;
import com.fraus.hotel.auth.domain.db.UserEntity;
import com.fraus.hotel.auth.domain.db.UserRepository;
import com.fraus.hotel.auth.web.dto.UserRegisterDto;
import com.fraus.hotel.auth.web.dto.UserDto;
import com.fraus.hotel.exception.UserAlreadyExistsException;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;

    @Transactional
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByUsername(userRegisterDto.getUsername())) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует");
        }
        var user = UserEntity.builder()
                .username(userRegisterDto.getUsername())
                .email(userRegisterDto.getEmail())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .role(RoleType.ROLE_USER)
                .enabled(true)
                .build();
        userRepository.save(user);
    }

    public UserDto findUserByUsername(UserDetails user, Pageable pageable) {
        var username = user.getUsername();
        var foundedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: username=" + username));
        Page<ReservationEntity> reservations = reservationRepository.findByUser(foundedUser, pageable);
        Page<ReservationResponseDto> reservationsDto = reservations.map(reservationMapper::toReservationResponseDto);
        return new UserDto(
                foundedUser.getUsername(),
                foundedUser.getEmail(),
                reservationsDto
        );
    }
}
