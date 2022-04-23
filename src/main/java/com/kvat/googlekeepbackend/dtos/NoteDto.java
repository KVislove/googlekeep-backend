package com.kvat.googlekeepbackend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NoteDto {

    private int id;

    @NotEmpty(message = "title cannot be empty")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Content should not be less than 10 characters")
    private String content;

    private Date createdDate;

    private UserDto user;

}
