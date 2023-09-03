package com.shin.ricu.service;

import com.shin.ricu.dto.MemberDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.exception.MemberIDIsNotExistException;
import com.shin.ricu.exception.MemberNicknameIsNotExistException;

public interface MemberService {
    public String joinMember(MemberDTO memberDTO) throws MemberIDExistException;

    public MemberDTO getMemberByID(String memberID) throws MemberIDIsNotExistException;

    public MemberDTO getMemberByNickname(String nickname) throws MemberNicknameIsNotExistException;
    public int isAvailableNickname(String nickname);
    public int isAvailableID(String memberID);

    public int isAvailablePassword(String password, String passwordCheck);

    public void editMember(MemberDTO memberDTO);
}
