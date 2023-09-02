package com.shin.ricu.dto.board;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime regDate;
}
