package com.shin.ricu.service;

import com.shin.ricu.domain.Member;
import com.shin.ricu.domain.MemberRole;
import com.shin.ricu.dto.MemberDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.exception.MemberIDIsNotExistException;
import com.shin.ricu.exception.MemberNicknameIsNotExistException;
import com.shin.ricu.repository.MemberRepository;
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
    public MemberDTO getMemberByID(String memberID) throws MemberIDIsNotExistException {
        Member member = memberRepository.findById(memberID).orElseThrow(() -> new MemberIDIsNotExistException());
        return entityToDTO(member);
    }

    @Override
    public MemberDTO getMemberByNickname(String nickname) throws MemberNicknameIsNotExistException {

        Member member = memberRepository.getMemberByNickName(nickname);
        if(member == null) throw new MemberNicknameIsNotExistException();
        return entityToDTO(member);
    }

    @Override
    public int isAvailableNickname(String nickname)
    {
        if(nickname == null || nickname.length() < 3 || nickname.length() > 20 ||
                    !nickname.matches("[0-9|a-z|A-Z]*")) return -1;
        if(memberRepository.existsByNickname(nickname)) return 1;
        return 0;
    }

    @Override
    public int isAvailableID(String memberID)
    {
        if(memberID == null || memberID.length() < 3 || memberID.length() >  20 ||
                !memberID.matches("[0-9|a-z|A-Z]*")) return -1;
        if(memberRepository.existsById(memberID)) return 1;
        return 0;
    }

    public int isAvailablePassword(String password, String passwordCheck)
    {
        if(password == null || password.length() < 3 || password.length() > 16 ||
                !password.matches(".*[0-9].*") || !password.matches(".*[a-zA-z].*") || !password.matches("^[a-zA-Z0-9]*"))
            return -1;
        log.info(password + " VS " + passwordCheck);
        if(!password.equals(passwordCheck))
            return 1;
        return 0;
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
