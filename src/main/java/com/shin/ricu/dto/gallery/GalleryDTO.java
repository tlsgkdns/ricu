package com.shin.ricu.dto.gallery;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GalleryDTO
{
    @NotBlank(message = "galleryID cannot be Empty")
    private String galleryID;
    @NotBlank(message = "Title cannot be Empty")
    private String title;
    @NotBlank(message = "ManagerID cannot be Empty")
    private String managerID;
    @NotBlank(message = "ManagerNickname cannot be Empty")
    private String managerNickname;
    private String explanation;
    private String galleryImageName;
    private Long popularThreshold;
    private LocalDateTime lastModifiedDate;
}
