package com.example.boardandcomment.domain.friend;

import com.example.boardandcomment.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAllByTakeMember(Member member);
}
