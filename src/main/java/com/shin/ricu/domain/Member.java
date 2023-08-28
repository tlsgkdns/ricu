package com.shin.ricu.domain;

import com.shin.ricu.domain.image.ProfileImage;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"roleSet", "password"})
@Log4j2
public class Member extends BaseEntity{
    @Id
    private String memberID;
    @Column(length = 50, nullable = false, unique = true)
    private String nickname;
    @Column(length = 100, nullable = false)
    private String email;
    private String password;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid")
    private ProfileImage profileImage;

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

    public void setProfileImage(String uuid, String fileName)
    {
        ProfileImage image = new ProfileImage(uuid, fileName, this);
        profileImage = image;
        log.info(image + " Image Added!");
    }
    public void editProfileInfo(String nickname, String email, String uuid, String fileName)
    {
        setProfileImage(uuid, fileName);
        changeMemberInfo(nickname, email);
    }

    public void changeMemberInfo(String nickname, String email)
    {
        this.nickname = nickname;
        this.email = email;
    }
}
