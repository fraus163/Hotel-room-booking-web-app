package com.fraus.hotel.reservation.web;

import com.fraus.hotel.reservation.domain.ReservationService;
import com.fraus.hotel.reservation.web.dto.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public String reserveRoom(
            @ModelAttribute(name = "reservationDto") ReservationDto reservationDto,
            @AuthenticationPrincipal UserDetails user,
            RedirectAttributes redirectAttributes
    ) {
        reservationService.reserveRoom(reservationDto, user);
        redirectAttributes.addFlashAttribute("successMessage", "Номер успешно забронирован!");
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/cancel")
    public String cancelReservation(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserDetails user,
            RedirectAttributes redirectAttributes
    ) {
        reservationService.cancelReservation(id, user);
        redirectAttributes.addFlashAttribute("successMessage", "Бронирование успешно отменено");
        return "redirect:/profile";
    }
}
