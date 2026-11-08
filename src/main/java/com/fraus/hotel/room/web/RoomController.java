package com.fraus.hotel.room.web;

import com.fraus.hotel.reservation.web.dto.ReservationDto;
import com.fraus.hotel.room.domain.RoomService;
import com.fraus.hotel.room.web.dto.RoomDto;
import com.fraus.hotel.room.web.dto.RoomFilterDto;
import com.fraus.hotel.util.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public String handlerRoomsPage(
            @PageableDefault(size = 8) Pageable pageable,
            @ModelAttribute("filter") RoomFilterDto roomFilterDto,
            Model model
    ) {
        org.springframework.data.domain.Page<RoomDto> rooms = roomService.getRoomsByFilter(pageable, roomFilterDto);

        int totalPages = rooms.getTotalPages();
        int currentPage = rooms.getNumber() + 1;

        int startPage = Page.startPage(totalPages, currentPage);
        int endPage = Page.endPage(totalPages, currentPage);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("rooms", rooms);
        model.addAttribute("filter", RoomFilterDto.builder()
                .name(roomFilterDto.getName())
                .priceFrom(roomFilterDto.getPriceFrom())
                .priceTo(roomFilterDto.getPriceTo())
                .area(roomFilterDto.getArea())
                .people(roomFilterDto.getPeople())
                .build()
        );

        return "pages/rooms/rooms";
    }

    @GetMapping("/{id}")
    public String handlerCurrentRoomPage(
            @PathVariable("id") Long id,
            Model model
    ) {
        RoomDto foundedRoom = roomService.getRoomById(id);
        model.addAttribute("room", foundedRoom);
        model.addAttribute("reservation", new ReservationDto());
        return "pages/rooms/current-room";
    }
}
