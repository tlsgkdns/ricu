package com.shin.ricu.dto.board;

import com.shin.ricu.domain.Comment;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private String galleryID;

    private Long bno;

    private String title;

    private String content;

    private String writer;
    private Long views;

    private LocalDateTime regDate;
}
