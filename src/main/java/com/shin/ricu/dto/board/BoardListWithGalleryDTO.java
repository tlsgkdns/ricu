package com.shin.ricu.dto.board;

import com.querydsl.core.annotations.QueryProjection;
import com.shin.ricu.domain.Board;
import com.shin.ricu.domain.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Data
@Log4j2
public class BoardListWithGalleryDTO {
    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private Long views;
    private Long commentCount;
}
