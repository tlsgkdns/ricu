package com.shin.ricu.dto.board;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTOForMembers {
    private String galleryID;
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private Long views;
    private Long likeCount;
    private Long commentCount;
    private LocalDateTime regDate;
}
