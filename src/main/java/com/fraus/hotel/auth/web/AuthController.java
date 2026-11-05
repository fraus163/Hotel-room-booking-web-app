package com.fraus.hotel.auth.web;

import com.fraus.hotel.auth.domain.AuthService;
import com.fraus.hotel.auth.web.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
            @PageableDefault(page = 0, size = 5) Pageable pageable,
            Model model,
            @AuthenticationPrincipal UserDetails user
    ) {
        var userDto = authService.findUserByUsername(user, pageable);

        int totalPages = userDto.reservations().getTotalPages();
        int currentPage = userDto.reservations().getNumber() + 1;

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

        model.addAttribute("user", userDto);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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
