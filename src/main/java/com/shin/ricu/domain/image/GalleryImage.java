package com.shin.ricu.domain.image;

import com.shin.ricu.domain.Gallery;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "gallery")
public class GalleryImage extends Image {

    @OneToOne(mappedBy = "galleryImage")
    private Gallery gallery;

    public GalleryImage(String uuid, String fileName, Gallery gallery)
    {
        super(uuid, fileName);
        this.gallery = gallery;
    }
}
