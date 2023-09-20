package com.example.boardandcomment.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardRequestDto {
    private String title;
    private String content;
}
