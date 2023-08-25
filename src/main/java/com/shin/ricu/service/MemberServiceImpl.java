package com.shin.ricu.service;

import com.shin.ricu.domain.Member;
import com.shin.ricu.domain.MemberRole;
import com.shin.ricu.dto.MemberJoinDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.repository.MemberRepository;
import com.shin.ricu.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Member joinMember(MemberJoinDTO memberJoinDTO) throws MemberIDExistException
    {
        if(memberRepository.existsById(memberJoinDTO.getMemberID()))
        {
            throw new MemberIDExistException();
        }
        Member member = modelMapper.map(memberJoinDTO, Member.class);

        member.changePassword(passwordEncoder.encode(memberJoinDTO.getPassword()));
        member.addRole(MemberRole.USER);

        log.info("Join the Member....................");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member);
        return member;
    }
}
