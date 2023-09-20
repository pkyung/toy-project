package com.example.boardandcomment.web;

import com.example.boardandcomment.service.LoginService;
import com.example.boardandcomment.web.dto.LoginRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Controller
public class LoginController {
    private final LoginService loginService;

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        if (loginService.login(loginRequestDto)) {
            Cookie memberId = new Cookie("memberId", loginRequestDto.getUuid());
            response.addCookie(memberId);
            return "redirect:/board";
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "login";
    }

}
