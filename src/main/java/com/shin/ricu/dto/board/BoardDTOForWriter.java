package com.shin.ricu.dto.board;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTOForWriter {
    private String galleryID;

    private Long bno;

    private String title;

    private String content;

    private String writer;
}
