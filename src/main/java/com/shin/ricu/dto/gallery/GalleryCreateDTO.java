package com.shin.ricu.dto.gallery;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "galleryID cannot be Empty")
    private String galleryID;
    private String explanation;
    @NotBlank(message = "Manager cannot be Empty")
    private String manager;
    @NotBlank(message = "Title cannot be Empty")
    private String title;
    private String galleryImage;

    public void setManager(String manager)
    {
        this.manager = manager;
    }
}
