package com.shin.ricu;

import com.shin.ricu.domain.Member;
import com.shin.ricu.domain.MemberRole;
import com.shin.ricu.dto.MemberDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.repository.MemberRepository;
import com.shin.ricu.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Log4j2
@SpringBootTest
public class MemberTests {
    @Autowired
    MemberRepository userRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void userTest()
    {
        String uid = "firstUser", em = "firstUser@gmail.com";
        Member user = Member.builder()
                .memberID(uid)
                .email(em)
                .nickname("hello User!")
                .password("userPassword").
                build();
        user.addRole(MemberRole.USER);
        userRepository.save(user);
        Assertions.assertTrue(userRepository.count() > 0);
        Optional<Member> newUser = userRepository.findById(uid);
        Assertions.assertNotNull(newUser);
    }

    @Test
    public void joinTest()
    {
        MemberDTO dto = MemberDTO.builder()
                .memberID("join Test")
                .email("test@join.com")
                .password("pass")
                .nickname("Hello")
                .build();
        try
        {
            memberService.joinMember(dto);
        } catch (MemberIDExistException e)
        {

        }
        Optional<Member> newUser = userRepository.findById("join Test");
        log.info(newUser);
    }

    @BeforeEach
    public void deleteDatabase()
    {
        userRepository.deleteAll();
    }
}
