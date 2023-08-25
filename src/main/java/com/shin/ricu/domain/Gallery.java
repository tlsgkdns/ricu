package com.shin.ricu.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@ToString(exclude = "galleryImage")
@Log4j2
public class Gallery extends BaseEntity{
    @Id
    private String galleryID;
    private String title;
    private String manager;
    private String explanation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid")
    private GalleryImage galleryImage;
    public void setImage(String uuid, String fileName)
    {
        GalleryImage image = GalleryImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .gallery(this)
                .build();
        log.info(image);
        galleryImage = image;
    }
}