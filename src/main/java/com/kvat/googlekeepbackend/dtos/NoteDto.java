package com.kvat.googlekeepbackend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteDto {
    private int id;
    private String title;
    private String content;
}
