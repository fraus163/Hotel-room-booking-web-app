package com.fraus.hotel.auth.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {

    private String username;

    private String email;

    private String password;
}
