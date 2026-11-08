package com.fraus.hotel.auth.web;

import com.fraus.hotel.auth.domain.AuthService;
import com.fraus.hotel.auth.web.dto.UserRegisterDto;
import com.fraus.hotel.util.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/")
    public String handlerHomePage() {
        return "index";
    }

    @GetMapping("/profile")
    public String handlerProfilePage(
            @PageableDefault(page = 0, size = 5) Pageable pageable,
            Model model,
            @AuthenticationPrincipal UserDetails user
    ) {
        var userDto = authService.findUserByUsername(user, pageable);

        int totalPages = userDto.reservations().getTotalPages();
        int currentPage = userDto.reservations().getNumber() + 1;

        int startPage = Page.startPage(totalPages, currentPage);
        int endPage = Page.endPage(totalPages, currentPage);

        model.addAttribute("userDto", userDto);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "pages/auth/profile";
    }

    @GetMapping("/login")
    public String handlerLoginPage() {
        return "pages/auth/login";
    }

    @GetMapping("/register")
    public String handlerRegisterPage(Model model) {
        model.addAttribute("registerDto", new UserRegisterDto());
        return "pages/auth/register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute(name = "registerDto") UserRegisterDto userRegisterDto
    ) {
        authService.registerUser(userRegisterDto);
        return "redirect:/";
    }
}
