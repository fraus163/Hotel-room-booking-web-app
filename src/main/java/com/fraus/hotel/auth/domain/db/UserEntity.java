package com.fraus.hotel.auth.domain.db;

import com.fraus.hotel.reservation.domain.db.ReservationEntity;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.NonNull;

import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private RoleType role;

    @Column(name = "password", nullable = false, length = 120)
    private String password;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "username", nullable = false, length = 25)
    private String username;

    @NonNull
    @OneToMany(mappedBy = "user")
    private Set<ReservationEntity> reservations = new LinkedHashSet<>();
}
