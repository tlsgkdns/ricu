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
    private String managerID;
    private String managerNickname;
    private String explanation;
    private String galleryImageName;
}