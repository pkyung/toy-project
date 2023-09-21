package com.example.boardandcomment.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentResponseDto {
    private Long id;
    private Long boardId;
    private String content;
    private String writer;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

}
