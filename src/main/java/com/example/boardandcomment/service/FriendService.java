package com.example.boardandcomment.service;

import com.example.boardandcomment.domain.friend.FriendRepository;
import com.example.boardandcomment.domain.member.Member;
import com.example.boardandcomment.domain.member.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

@AllArgsConstructor
@Service
public class FriendService {
    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    public List<Member> getFriend(Cookie memberId) {
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));
        return member.getFriends();
    }
}
