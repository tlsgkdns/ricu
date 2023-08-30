package com.shin.ricu.dto.gallery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryListAllDTO
{
    private String galleryID;
    private String title;
    private String explanation;
    private String galleryImageName;
    private String managerMemberID;
    private LocalDate createDate;
}
