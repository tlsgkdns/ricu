package com.shin.ricu.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardModifyDTO {
    private String galleryID;

    private Long bno;

    private String title;

    private String content;

    private String writer;
}
