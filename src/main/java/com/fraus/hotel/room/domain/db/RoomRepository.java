package com.fraus.hotel.room.domain.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @Query("""
            select r
            from RoomEntity r
            where (cast(:name as string) is null or lower(r.name) like lower(concat(cast(:name as string), '%'))) and
                  ((:priceFrom is null and :priceTo is null) or 
                   (:priceFrom is not null and :priceTo is not null and r.pricePerNight >= :priceFrom and r.pricePerNight <= :priceTo) or
                   (:priceFrom is null and :priceTo is not null and r.pricePerNight <= :priceTo) or
                   (:priceFrom is not null and :priceTo is null and r.pricePerNight >= :priceFrom)) and
                  (:area is null or r.area = :area) and
                  (:people is null or r.people = :people) and
                  (:enabled = r.enabled)
            """)
    Page<RoomEntity> findByFilterAndEnabled(
            Pageable pageable,
            String name,
            BigDecimal priceFrom,
            BigDecimal priceTo,
            Integer area,
            Integer people,
            Boolean enabled
    );
}
