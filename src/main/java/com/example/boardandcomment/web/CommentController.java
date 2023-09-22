package com.example.boardandcomment.web;

import com.example.boardandcomment.service.CommentService;
import com.example.boardandcomment.web.dto.CommentRequestDto;
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
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comment/{boardId}")
    public String getComment(@PathVariable Long boardId, Model model, @CookieValue(value = "memberId", required = false) Cookie memberCookie) {
        model.addAttribute("comment", commentService.getComment(boardId, memberCookie));
        return "comment";
    }

    @PostMapping("/comment/{boardId}")
    public String saveComment(@PathVariable Long boardId, CommentRequestDto commentRequestDto, @CookieValue(value = "memberId", required = false) Cookie memberCookie) {
        commentService.saveComment(commentRequestDto, boardId, memberCookie);
        return "redirect:/board";
    }
}
