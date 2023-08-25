package com.shin.ricu.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "gallery")
public class GalleryImage{
    @Id
    private String uuid;

    private String fileName;

    @OneToOne(mappedBy = "galleryImage")
    private Gallery gallery;

    public String getLink()
    {
        return uuid + "_" + fileName;
    }
}
