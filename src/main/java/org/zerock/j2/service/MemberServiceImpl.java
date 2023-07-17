package org.zerock.j2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.j2.dto.MemberDTO;
import org.zerock.j2.entity.Member;
import org.zerock.j2.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public static final class MemberLoginException extends RuntimeException {
        //runtimeexception으로 잡은 이유?
        public MemberLoginException(String msg){
            super(msg);
        }

    }

    @Override
    public MemberDTO login(String email, String pw) {

        MemberDTO memberDTO = null;

        try {
            Optional<Member> result = memberRepository.findById(email);

            Member member = result.orElseThrow();

            if(member.getPw().equals(pw) == false){
               throw new MemberLoginException("Password Incorrect");
            }

            memberDTO = MemberDTO.builder()
                    .email(member.getEmail())
                    .pw("")
                    .nickName(member.getNickName())
                    .admin(member.isAdmin())
                    .build();

        }catch (Exception e){
            throw new MemberLoginException(e.getMessage());
        }
        return memberDTO;
    }
}
