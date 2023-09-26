package com.example.boardandcomment.domain.like;

import com.example.boardandcomment.domain.board.Board;
import com.example.boardandcomment.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByBoardAndMember(Board board, Member member);
    List<Like> findByBoard(Board board);
}
