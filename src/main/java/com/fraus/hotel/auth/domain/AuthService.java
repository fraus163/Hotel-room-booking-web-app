package com.fraus.hotel.auth.domain;

import com.fraus.hotel.auth.domain.db.UserEntity;
import com.fraus.hotel.auth.domain.db.UserRole;
import com.fraus.hotel.auth.web.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterDto registerDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(UserRole.ROLE_USER);
        user.setEnabled(true);

        userRepository.save(user);
    }
}
