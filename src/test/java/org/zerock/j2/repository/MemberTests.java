package org.zerock.j2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.j2.entity.Member;

import java.util.Optional;

@SpringBootTest
public class MemberTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testInsert(){

        Member member = Member.builder()
                .email("soohosuh3@gmail.com")
                .pw("1111")
                .nickName("USER00")
                .build();

        memberRepository.save(member);

    }

    @Test
    public void testRead() {
        String email = "user00@aaa.com";

        Optional<Member> result = memberRepository.findById(email);

        Member member = result.orElseThrow();

        System.out.println(member);
    }
}
