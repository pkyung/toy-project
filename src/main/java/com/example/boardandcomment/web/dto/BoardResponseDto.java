package com.example.boardandcomment.web.dto;

import com.example.boardandcomment.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String image;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

}
