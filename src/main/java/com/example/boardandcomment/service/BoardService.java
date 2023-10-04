package com.example.boardandcomment.service;

import com.example.boardandcomment.domain.board.Board;
import com.example.boardandcomment.domain.board.BoardRepository;
import com.example.boardandcomment.domain.member.Member;
import com.example.boardandcomment.domain.member.MemberRepository;
import com.example.boardandcomment.web.dto.BoardRequestDto;
import com.example.boardandcomment.web.dto.BoardResponseDto;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public List<BoardResponseDto> allBoard() {
        List<Board> boards = boardRepository.findAllDesc();
        List<BoardResponseDto> boardDto = new ArrayList<>();
        for (int i = 0; i < boards.size(); i++) {
            BoardResponseDto boardResponseDto = new BoardResponseDto();
            boardResponseDto.setId(boards.get(i).getId());
            boardResponseDto.setTitle(boards.get(i).getTitle());
            boardResponseDto.setWriter(boards.get(i).getWriter().getName());
            boardResponseDto.setCreateAt(boards.get(i).getCreateAt());
            boardResponseDto.setModifiedAt(boards.get(i).getModifiedAt());
            boardDto.add(boardResponseDto);
        }
        return boardDto;
    }

    public BoardResponseDto oneBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setContent(board.getContent());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setCreateAt(board.getCreateAt());
        boardResponseDto.setModifiedAt(board.getModifiedAt());
        boardResponseDto.setWriter(board.getWriter().getName());
        return boardResponseDto;
    }

    public Board saveBoard(BoardRequestDto boardRequestDto, Cookie memberCookie, String fileName) {
        String title = boardRequestDto.getTitle();
        String content = boardRequestDto.getContent();
        Member member = memberRepository.findByUuid(memberCookie.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다"));

        return boardRepository.save(Board.builder()
                .title(title)
                .writer(member)
                .content(content)
                .image(fileName)
                .build());
    }

    @Transactional
    public Long update(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));
        board.update(boardRequestDto.getTitle(), boardRequestDto.getContent());
        return id;
    }

    public Long delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));
        boardRepository.delete(board);
        return id;
    }

    /*
    본인 게시글인지 확인하는 서비스
     */
    public Boolean isMine(Long id, Cookie memberCookie) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));
        Member member = memberRepository.findByUuid(memberCookie.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다"));

        if (board.getWriter().equals(member)) {
            return true;
        } else {
            return false;
        }
    }


}
