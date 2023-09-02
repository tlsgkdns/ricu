package com.shin.ricu.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class MemberDTO
{
    @NotBlank(message = "Nickname cannot be Empty")
    private String nickname;
    @NotBlank(message = "Password cannot be Empty")
    private String password;
    private String email;
    @NotBlank(message = "MemberID cannot be Empty")
    private String memberID;
    private String profileImageName;
    private LocalDateTime regDate;
}
