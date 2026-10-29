package com.fraus.hotel.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String handlerHome() {
        return "index";
    }

    @GetMapping("/profile")
    public String handlerProfile() {
        return "profile";
    }

    @GetMapping("/rooms")
    public String handlerRooms() {
        return "rooms";
    }
}
