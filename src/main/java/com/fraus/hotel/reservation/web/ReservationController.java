package com.fraus.hotel.reservation.web;

import com.fraus.hotel.reservation.domain.ReservationService;
import com.fraus.hotel.reservation.web.dto.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public String reserveRoom(
            @ModelAttribute(name = "reservationDto") ReservationDto reservationDto,
            @AuthenticationPrincipal UserDetails user
    ) {
        reservationService.reserveRoom(reservationDto, user);
        return "redirect:/rooms";
    }
}

