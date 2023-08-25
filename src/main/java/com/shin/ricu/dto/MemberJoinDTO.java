package com.shin.ricu.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberJoinDTO
{
    private String nickname;
    private String password;
    private String email;
    private String memberID;
}
