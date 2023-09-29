package com.example.boardandcomment.domain.friendRequest;

import com.example.boardandcomment.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findAllByTakeMember(Member member);
    FriendRequest findByTakeMemberAndGiveMember(Member giveMember, Member takeMember);
}
