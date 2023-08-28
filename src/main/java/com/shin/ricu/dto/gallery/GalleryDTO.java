package com.shin.ricu.dto.gallery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GalleryDTO
{
    private String galleryID;
    private String title;
    private String manager;
    private String explanation;
    private String galleryImageName;
}
