package com.example.boardandcomment.web;

import com.example.boardandcomment.service.MemberService;
import com.example.boardandcomment.web.dto.MemberRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member")
    public String member() {
        return "member";
    }

    @PostMapping("/member")
    public String saveMember(MemberRequestDto memberRequestDto) {
        memberService.saveMember(memberRequestDto);
        return "redirect:/login";
    }
}
