package com.example.boardandcomment.service;

import com.example.boardandcomment.domain.member.Member;
import com.example.boardandcomment.domain.member.MemberRepository;
import com.example.boardandcomment.web.dto.MemberRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(MemberRequestDto memberRequestDto) {
        String uuid = memberRequestDto.getUuid();
        String name = memberRequestDto.getName();
        String password = memberRequestDto.getPassword();
        return memberRepository.save(Member.builder()
                .uuid(uuid)
                .password(password)
                .name(name)
                .build());
    }

}
