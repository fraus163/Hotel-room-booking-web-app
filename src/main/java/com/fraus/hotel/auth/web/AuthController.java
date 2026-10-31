package com.fraus.hotel.auth.web;

import com.fraus.hotel.auth.domain.AuthService;
import com.fraus.hotel.auth.web.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping("/")
    public String handlerHome() {
        return "index";
    }

    @GetMapping("/profile")
    public String handlerProfile(
            Model model,
            @AuthenticationPrincipal UserDetails user
    ) {
        var userDto = authService.findUserByUsername(user);
        model.addAttribute("user", userDto);
        return "pages/profile";
    }

    @GetMapping("/login")
    public String handlerLoginPage() {
        return "pages/auth/login";
    }

    @GetMapping("/register")
    public String handlerRegisterPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "pages/auth/register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute(name = "registerDto") RegisterDto registerDto
    ) {
        log.info("Controller registerUser is called");
        authService.registerUser(registerDto);
        return "redirect:/";
    }
}
