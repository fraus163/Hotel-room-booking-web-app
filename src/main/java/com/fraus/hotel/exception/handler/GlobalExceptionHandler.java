package com.fraus.hotel.exception.handler;

import com.fraus.hotel.exception.RoomAlreadyReservedException;
import com.fraus.hotel.exception.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Что-то пошло не так. Пожалуйста, попробуйте позже.");
        model.addAttribute("httpStatus", 500);
        log.error("Internal server error. Message: {}", ex.getMessage(), ex);
        return "pages/error";
    }

    @ExceptionHandler(RoomAlreadyReservedException.class)
    public String handleRoomAlreadyReservedException(
            RoomAlreadyReservedException ex,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ) {
        log.warn("Room reservation conflict. Message: {}", ex.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/rooms");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        log.warn("User is already exists. Message: {}", ex.getMessage());
        return "pages/auth/register";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoResourceFoundException(NoResourceFoundException ex, Model model) {
        model.addAttribute("errorMessage", "Страница не найдена");
        model.addAttribute("httpStatus", 404);
        log.error("Resource not found. Message: {}", ex.getMessage());
        return "pages/error";
    }
}
