package com.example.boardandcomment.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberRequestDto {
    private String uuid;
    private String password;
    private String name;
}
