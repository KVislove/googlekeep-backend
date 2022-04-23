package com.kvat.googlekeepbackend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Username cannot be empty !!")
    private String username;

    @NotEmpty(message = "password cannot be empty !!")
    private String password;
}
