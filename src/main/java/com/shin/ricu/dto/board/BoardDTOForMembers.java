package com.shin.ricu.dto.board;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Title cannot be Empty")
    private String title;
    @NotBlank(message = "Content Cannot be Empty")
    private String content;
    private String writer;
    private Long views;
    private Long likeCount;
    private Long commentCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime regDate;
}
