package com.shin.ricu.service;

import com.shin.ricu.domain.Member;
import com.shin.ricu.dto.MemberJoinDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.security.dto.MemberSecurityDTO;

public interface MemberService {
    public Member joinMember(MemberJoinDTO memberJoinDTO) throws MemberIDExistException;
}
