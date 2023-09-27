package com.example.boardandcomment.web;

import com.example.boardandcomment.service.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;

@AllArgsConstructor
@Controller
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/friend")
    public String getFriend(@CookieValue(value = "memberId", required = false) Cookie memberCookie, Model model) {
        model.addAttribute("friends", friendService.getFriend(memberCookie));
        return "friendList";
    }

    @GetMapping("/friend/search")
    public String getSearch(@CookieValue(value = "memberId", required = false) Cookie memberCookie, String keyword, Model model) {
        model.addAttribute("friends", friendService.getSearch(memberCookie, keyword));
        return "friendSearch";
    }
}
