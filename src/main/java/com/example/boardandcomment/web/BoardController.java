package com.example.boardandcomment.web;

import com.example.boardandcomment.service.BoardService;
import com.example.boardandcomment.web.dto.BoardRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

@AllArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String getBoard(Model model, @CookieValue(value = "memberId", required = false) Cookie memberCookie) {
        if (memberCookie != null) {
            model.addAttribute("boards", boardService.allBoard());
            return "board";
        } else {
            return "login";
        }
    }

    @GetMapping("/board/{id}")
    public String getBoardById(@PathVariable Long id, Model model, @CookieValue(value = "memberId", required = false) Cookie memberCookie) {
        model.addAttribute("board", boardService.oneBoard(id));
        model.addAttribute("mine", boardService.isMine(id, memberCookie));
        return "boardOne";
    }

    @PostMapping("/board")
    public String saveBoard(BoardRequestDto boardRequestDto, @CookieValue(value = "memberId", required = false) Cookie memberCookie) {
        boardService.saveBoard(boardRequestDto, memberCookie);
        return "redirect:/board";
    }

    @PutMapping("/board/{id}")
    public String updateBoard(@PathVariable Long id, BoardRequestDto boardRequestDto) {
        boardService.update(id, boardRequestDto);
        return "redirect:/board";
    }

    @DeleteMapping("/board/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board";
    }

    // boardWrite.mustache 로 이동하는 매핑
    @GetMapping("/boardWrite")
    public String getWriteBrowser() {
        return "boardWrite";
    }

    // boardUpdate.mustache 로 이동하는 매핑
    @GetMapping("/boardUpdate/{id}")
    public String getUpdateBrowser(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.oneBoard(id));
        model.addAttribute("id", id);
        return "boardUpdate";
    }


}
