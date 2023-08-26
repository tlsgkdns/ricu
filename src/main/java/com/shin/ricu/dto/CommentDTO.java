package com.shin.ricu.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDTO {
    private Long commentID;
    private Long bno;
    private String commentText;
    private String writer;
}
