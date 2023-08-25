package com.shin.ricu.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDTO {
    private Long bno;
    private String content;
    private String writer;
}
