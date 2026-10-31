package com.fraus.hotel.auth.domain;

import com.fraus.hotel.auth.domain.db.RoleType;
import com.fraus.hotel.auth.domain.db.UserEntity;
import com.fraus.hotel.auth.domain.db.UserRepository;
import com.fraus.hotel.auth.web.dto.RegisterDto;
import com.fraus.hotel.auth.web.dto.UserDto;
import com.fraus.hotel.auth.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public void registerUser(RegisterDto registerDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(RoleType.ROLE_USER);
        user.setEnabled(true);

        userRepository.save(user);
    }

    public UserDto findUserByUsername(UserDetails user) {
        var username = user.getUsername();
        var foundedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: username=" + username));
        return userMapper.toUserDto(foundedUser);
    }
}
