package com.shin.ricu.domain;

import com.shin.ricu.domain.image.GalleryImage;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

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
    private String explanation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid")
    private GalleryImage galleryImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member manager;

    public void setImage(String uuid, String fileName)
    {
        GalleryImage image = new GalleryImage(uuid, fileName, this);
        log.info(image);
        galleryImage = image;
    }

    public void changeExplanation(String explanation)
    {
        this.explanation = explanation;
    }

}