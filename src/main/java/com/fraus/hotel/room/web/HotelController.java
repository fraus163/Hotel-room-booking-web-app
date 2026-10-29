package com.fraus.hotel.room.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {

    @GetMapping("/rooms")
    public String handlerRoomsPage() {
        return "pages/rooms";
    }
}
