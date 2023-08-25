package com.shin.ricu.dto;

import com.shin.ricu.domain.Comment;
import jakarta.persistence.Column;
import lombok.*;

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

    private List<Comment> commentList = new ArrayList<>();
}
