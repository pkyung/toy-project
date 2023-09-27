package com.example.boardandcomment.web;

import com.example.boardandcomment.domain.friend.Friend;
import com.example.boardandcomment.service.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/friend/{friendId}")
    public Friend saveFriendRequest(@PathVariable String friendId, @CookieValue(value = "memberId", required = false) Cookie memberCookie) {
        return friendService.saveFriendRequest(memberCookie, friendId);
    }

    @GetMapping("/friend/request")
    public String getFriendRequest(@CookieValue(value = "memberId", required = false) Cookie memberCookie, Model model) {
        model.addAttribute("friends", friendService.getFriendRequest(memberCookie));
        return "friendMail";
    }
}
