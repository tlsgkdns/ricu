package com.shin.ricu.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {
    private String memberID;
    private String nickname;
    private String password;
    private String email;

    private Map<String, Object> props;
    public MemberSecurityDTO(String id, String nickname, String password, String email,
                             Collection<? extends GrantedAuthority> authorities)
    {
        super(id, password, authorities);
        this.memberID = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }
    public String getName() {return this.memberID;}
}
