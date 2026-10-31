package com.fraus.hotel.auth.domain;

import com.fraus.hotel.auth.domain.db.UserEntity;
import com.fraus.hotel.auth.domain.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true, true, true,
                Set.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
