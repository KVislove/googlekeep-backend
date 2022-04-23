package com.kvat.googlekeepbackend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 3,max = 10,message = "username must be within 3-10 characters !!")
    private String username;

    @Email(message = "Please provide valid email !!")
    private String email;

    @NotEmpty
    @Size(min = 3,max = 25,message = "password should be within 3-25 characters !!")
    private String password;
}
