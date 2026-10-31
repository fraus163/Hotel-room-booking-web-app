package com.fraus.hotel.room.domain.db;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tags")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 25)
    private String name;
}
