package com.shin.ricu.domain;

import com.shin.ricu.domain.image.GalleryImage;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@ToString(exclude = "galleryImage")
@Log4j2
public class Gallery extends BaseEntity{
    @Id
    @Column(length = 30)
    private String galleryID;
    @Column(length = 30)
    private String title;
    @Column(length = 500)
    private String explanation;
    @Builder.Default
    private Long popularThreshold = 5L;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid")
    private GalleryImage galleryImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member manager;

    @CreatedDate
    private LocalDateTime lastModifiedDate;

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

    public void setLastModifiedDate(LocalDateTime lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
    }

}