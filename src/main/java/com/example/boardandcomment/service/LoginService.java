package com.example.boardandcomment.service;

import com.example.boardandcomment.domain.member.Member;
import com.example.boardandcomment.domain.member.MemberRepository;
import com.example.boardandcomment.web.dto.LoginRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LoginService {
    private final MemberRepository memberRepository;

    public Boolean login(LoginRequestDto loginRequestDto) {
        String uuid = loginRequestDto.getUuid();
        String password = loginRequestDto.getPassword();

        Optional<Member> member = memberRepository.findByUuid(uuid);

        if (member.isPresent()) {
            if (member.get().getPassword().equals(password))
                return true;
            else
                return false;
        } else {
            return false;
        }

    }

}
