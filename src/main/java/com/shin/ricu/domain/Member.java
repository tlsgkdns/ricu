package com.shin.ricu.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{
    @Id
    private String memberID;
    @Column(length = 50, nullable = false)
    private String nickname;
    @Column(length = 100, nullable = false)
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String password)
    {
        this.password = password;
    }

    public void addRole(MemberRole memberRole)
    {
        this.roleSet.add(memberRole);
    }
}
