package com.shin.ricu.service;

import com.shin.ricu.domain.Member;
import com.shin.ricu.dto.MemberDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.security.dto.MemberSecurityDTO;

public interface MemberService {
    public String joinMember(MemberDTO memberDTO) throws MemberIDExistException;

    public MemberDTO getMember(String memberID);

    public int isAvailableNickname(String nickname);
    public int isAvailableID(String memberID);

    public int isAvailablePassword(String password, String passwordCheck);

    public void editMember(MemberDTO memberDTO);
}
