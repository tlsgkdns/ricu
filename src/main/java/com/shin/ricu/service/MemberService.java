package com.shin.ricu.service;

import com.shin.ricu.domain.Member;
import com.shin.ricu.dto.MemberDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.security.dto.MemberSecurityDTO;

public interface MemberService {
    public String joinMember(MemberDTO memberDTO) throws MemberIDExistException;

    public MemberDTO getMember(String memberID);

    public boolean isExistByNickname(String nickname);
    public boolean isExistByID(String memberID);

    public void editMember(MemberDTO memberDTO);
}
