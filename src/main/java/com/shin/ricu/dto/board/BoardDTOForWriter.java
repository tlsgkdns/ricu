package com.shin.ricu.dto.board;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTOForWriter {
    @NotBlank
    private String galleryID;
    private Long bno;

    @NotBlank(message = "Title cannot be Empty")
    private String title;

    @NotBlank(message = "Content cannot be Empty")
    private String content;

    @NotBlank
    private String writer;
}
