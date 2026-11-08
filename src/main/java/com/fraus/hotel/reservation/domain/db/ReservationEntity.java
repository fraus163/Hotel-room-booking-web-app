package com.fraus.hotel.reservation.domain.db;

import com.fraus.hotel.auth.domain.db.UserEntity;
import com.fraus.hotel.room.domain.db.RoomEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 25)
    private StatusType status;

    @Column(name = "arrival_date", nullable = false)
    private LocalDate arrivalDate;

    @Column(name = "full_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal fullPrice;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "days", nullable = false)
    private Integer days;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Builder
    public ReservationEntity(
            StatusType status,
            LocalDate arrivalDate,
            BigDecimal fullPrice,
            Instant createdAt,
            Integer days,
            RoomEntity room,
            UserEntity user
    ) {
        this.status = status;
        this.arrivalDate = arrivalDate;
        this.fullPrice = fullPrice;
        this.createdAt = createdAt;
        this.days = days;
        this.room = room;
        this.user = user;
    }
}
