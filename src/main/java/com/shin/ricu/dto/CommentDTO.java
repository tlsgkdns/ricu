package com.shin.ricu.dto;

import lombok.*;

import java.time.LocalDateTime;

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
    private String profileImageName;
    private LocalDateTime regDate;
}
