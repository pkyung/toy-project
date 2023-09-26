package com.example.boardandcomment.service;

import com.example.boardandcomment.domain.board.Board;
import com.example.boardandcomment.domain.board.BoardRepository;
import com.example.boardandcomment.domain.like.Like;
import com.example.boardandcomment.domain.like.LikeRepository;
import com.example.boardandcomment.domain.member.Member;
import com.example.boardandcomment.domain.member.MemberRepository;
import com.example.boardandcomment.web.dto.LikeResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public LikeResponseDto getLike(Long boardId, Cookie memberId) {
        LikeResponseDto likeResponseDto = new LikeResponseDto();

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));

        Optional<Like> like = likeRepository.findByBoardAndMember(board, member);
        List<Like> likeList = likeRepository.findByBoard(board);

        if (like.isPresent()) {
            likeResponseDto.setIsMine(true);
            likeResponseDto.setIsNotMine(false);
        } else {
            likeResponseDto.setIsMine(false);
            likeResponseDto.setIsNotMine(true);
        }

        likeResponseDto.setCnt(likeList.size());
        return likeResponseDto;
    }

    public Like saveLike(Long boardId, Cookie memberId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));

        Optional<Like> like = likeRepository.findByBoardAndMember(board, member);

        if (like.isPresent()) {
            return null;
        } else {
            return likeRepository.save(Like.builder()
                    .board(board)
                    .member(member)
                    .build());
        }
    }

}
