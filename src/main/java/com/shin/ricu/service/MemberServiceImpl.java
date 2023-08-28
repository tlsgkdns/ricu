package com.shin.ricu.service;

import com.shin.ricu.domain.Member;
import com.shin.ricu.domain.MemberRole;
import com.shin.ricu.dto.MemberDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.repository.MemberRepository;
import com.shin.ricu.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String joinMember(MemberDTO memberDTO) throws MemberIDExistException
    {
        log.info("register " + memberDTO);
        if(memberRepository.existsById(memberDTO.getMemberID()))
        {
            throw new MemberIDExistException();
        }
        Member member = dtoToEntity(memberDTO);
        member.changePassword(passwordEncoder.encode(memberDTO.getPassword()));
        member.addRole(MemberRole.USER);

        log.info("Join the Member....................");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member);
        return member.getMemberID();
    }

    @Override
    public MemberDTO getMember(String memberID) {
        Member member = memberRepository.findById(memberID).orElseThrow();
        if(member == null) return null;
        return entityToDTO(member);
    }

    @Override
    public boolean isExistByNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Override
    public boolean isExistByID(String memberID) {
        return memberRepository.existsById(memberID);
    }

    @Override
    public void editMember(MemberDTO memberDTO) {
        Member member = memberRepository.findById(memberDTO.getMemberID()).orElseThrow();

        if(memberDTO.getProfileImageName() == null)
            member.changeMemberInfo(memberDTO.getNickname(), memberDTO.getEmail());
        else
        {
            String[] arr = memberDTO.getProfileImageName().split("_");
            member.editProfileInfo(memberDTO.getNickname(), memberDTO.getEmail(), arr[0], arr[1]);
        }
        memberRepository.save(member);
    }

    private Member dtoToEntity(MemberDTO memberDTO)
    {
        Member member = modelMapper.map(memberDTO, Member.class);
        if(memberDTO.getProfileImageName() != null)
        {
            log.info(memberDTO.getProfileImageName());
            String[] names = memberDTO.getProfileImageName().split("_");
            member.setProfileImage(names[0], names[1]);
        }
        return member;
    }

    private MemberDTO entityToDTO(Member member)
    {
        MemberDTO memberDTO = MemberDTO.builder()
                .memberID(member.getMemberID())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .profileImageName((member.getProfileImage() != null) ? member.getProfileImage().getLink() : null)
                .regDate(member.getRegDate())
                .build();
        return memberDTO;
    }
}
