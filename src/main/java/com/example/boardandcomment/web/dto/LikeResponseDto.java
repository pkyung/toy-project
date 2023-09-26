package com.example.boardandcomment.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LikeResponseDto {
    private int cnt;
    private Boolean isMine;
    private Boolean isNotMine;
}
