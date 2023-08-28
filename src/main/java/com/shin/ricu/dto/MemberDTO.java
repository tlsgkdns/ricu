package com.shin.ricu.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class MemberDTO
{
    private String nickname;
    private String password;
    private String email;
    private String memberID;
    private String profileImageName;
    private LocalDateTime regDate;
}
