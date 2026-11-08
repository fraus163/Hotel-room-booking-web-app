package com.fraus.hotel.room.domain.db;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.NonNull;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "rooms")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NonNull
    @ManyToMany
    @JoinTable(name = "tags_rooms", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<TagEntity> tags = new LinkedHashSet<>();

    @Column(name = "price_per_night", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerNight;

    @Column(name = "area")
    private Integer area;

    @Column(name = "people")
    private Integer people;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Builder
    public RoomEntity(
            @NonNull Set<TagEntity> tags,
            BigDecimal pricePerNight,
            Integer area,
            Integer people,
            String description,
            String name,
            Boolean enabled
    ) {
        this.tags = tags;
        this.pricePerNight = pricePerNight;
        this.area = area;
        this.people = people;
        this.description = description;
        this.name = name;
        this.enabled = enabled;
    }
}
