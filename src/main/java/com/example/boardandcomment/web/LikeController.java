package com.example.boardandcomment.web;

import com.example.boardandcomment.domain.like.Like;
import com.example.boardandcomment.service.LikeService;
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
public class LikeController {

    private final LikeService likeService;
    @GetMapping("/like/{boardId}")
    public String getLike(@PathVariable Long boardId, @CookieValue(value = "memberId", required = false) Cookie memberCookie, Model model) {
        model.addAttribute("like", likeService.getLike(boardId, memberCookie));
        return "like";
    }

    @PostMapping("/like/{boardId}")
    public Like saveLike(@PathVariable Long boardId, @CookieValue(value = "memberId", required = false) Cookie memberCookie, Model model) {
        return likeService.saveLike(boardId, memberCookie);
    }
}
