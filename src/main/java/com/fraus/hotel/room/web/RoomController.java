package com.fraus.hotel.room.web;

import com.fraus.hotel.reservation.web.dto.ReservationDto;
import com.fraus.hotel.room.domain.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public String handlerRoomsPage(
            @PageableDefault(page = 0, size = 8) Pageable pageable,
            Model model
    ) {
        var rooms = roomService.getEnabledRooms(pageable);

        int totalPages = rooms.getTotalPages();
        int currentPage = rooms.getNumber() + 1;

        int startPage = 1;
        int endPage = totalPages;

        if (totalPages > 5) {
            startPage = Math.max(1, currentPage - 2);
            endPage = Math.min(totalPages, currentPage + 2);

            if (currentPage <= 3) {
                endPage = 5;
            } else if (currentPage >= totalPages -2) {
                startPage = totalPages - 4;
            }
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("reservationDto", new ReservationDto());
        model.addAttribute("rooms", rooms);

        return "pages/rooms";
    }
}
