package com.shin.ricu.domain.image;

import com.shin.ricu.domain.Member;
import com.shin.ricu.domain.image.Image;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
public class ProfileImage extends Image {
    @OneToOne(mappedBy = "profileImage")
    Member member;

    public ProfileImage(String uuid, String fileName, Member member)
    {
        super(uuid, fileName);
        this.member = member;
    }
}
