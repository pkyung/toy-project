package com.example.boardandcomment.service;

import com.example.boardandcomment.domain.friend.FriendRepository;
import com.example.boardandcomment.domain.member.Member;
import com.example.boardandcomment.domain.member.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
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

    public List<Member> getSearch(Cookie memberId, String keyword) {
        Member member = memberRepository.findByUuid(memberId.getValue()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));
        List<Member> searchList  = new ArrayList<>();
        List<Member> memberList = memberRepository.findByUuidContaining(keyword);

        for (int i = 0; i < memberList.size(); i++) {
            if (!memberList.get(i).equals(member)) {
                searchList.add(memberList.get(i));
            }
        }
        return searchList;
    }
}
