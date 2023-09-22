package com.example.boardandcomment.service;

import com.example.boardandcomment.domain.board.Board;
import com.example.boardandcomment.domain.board.BoardRepository;
import com.example.boardandcomment.domain.comment.Comment;
import com.example.boardandcomment.domain.comment.CommentRepository;
import com.example.boardandcomment.domain.member.Member;
import com.example.boardandcomment.domain.member.MemberRepository;
import com.example.boardandcomment.web.dto.CommentRequestDto;
import com.example.boardandcomment.web.dto.CommentResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public List<CommentResponseDto> getComment(Long boardId, Cookie memberId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        List<Comment> commentList = commentRepository.findByBoard(board);
        List<CommentResponseDto> commentDtoList = new ArrayList<>();
        for (int i = 0; i < commentList.size(); i++) {
            CommentResponseDto commentResponseDto = new CommentResponseDto();
            commentResponseDto.setId(commentList.get(i).getId());
            commentResponseDto.setWriter(commentList.get(i).getWriter().getName());
            commentResponseDto.setContent(commentList.get(i).getContent());
            commentResponseDto.setCreateAt(commentList.get(i).getCreateAt());
            commentResponseDto.setModifiedAt(commentList.get(i).getModifiedAt());
            commentResponseDto.setBoardId(commentList.get(i).getBoard().getId());
            if (commentList.get(i).getWriter().equals(member)) {
                commentResponseDto.setIsMine(true);
            } else {
                commentResponseDto.setIsMine(false);
            }
            commentDtoList.add(commentResponseDto);
        }
        return commentDtoList;
    }

    public Comment saveComment(CommentRequestDto commentRequestDto, Long boardId, Cookie memberId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        return commentRepository.save(Comment.builder()
                .content(commentRequestDto.getContent())
                .writer(member)
                .board(board)
                .build());
    }
}
