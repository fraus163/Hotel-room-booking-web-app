package com.fraus.hotel.room.web;

import com.fraus.hotel.reservation.web.dto.ReservationDto;
import com.fraus.hotel.room.domain.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public String handlerRoomsPage(Model model) {
        var rooms = roomService.getEnabledRooms();
        model.addAttribute("reservationDto", new ReservationDto());

        model.addAttribute("rooms", rooms);

        return "pages/rooms";
    }
}
