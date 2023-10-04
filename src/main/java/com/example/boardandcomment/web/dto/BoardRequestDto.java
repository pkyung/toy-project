package com.example.boardandcomment.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class BoardRequestDto {
    private String title;
    private String content;
    private MultipartFile image;
}
