package com.shin.ricu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryCreateDTO
{
    private String galleryID;
    private String explanation;
    private String manager;
    private String title;
    private String galleryImage;

    public void setManager(String manager)
    {
        this.manager = manager;
    }
}
