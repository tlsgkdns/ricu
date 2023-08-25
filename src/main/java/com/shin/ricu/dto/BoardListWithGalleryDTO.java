package com.shin.ricu.dto;

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
    private Long commentCount;

    @QueryProjection
    public BoardListWithGalleryDTO(Long bno, String title, String writer, LocalDateTime regDate, List<Comment> commentList)
    {
        this.bno = bno;
        this.title = title;
        this.writer = writer;
        this.regDate = regDate;
        this.commentCount = Long.valueOf(commentList.size());
        log.info("This is DTO which is " + this.title);
    }

    @QueryProjection
    public BoardListWithGalleryDTO(Board board)
    {
        this.bno = board.getBno();
        this.title = board.getTitle();
        if(board.getWriter() == null) this.writer = "Member";
        else this.writer = board.getWriter().getNickname();
        this.regDate = board.getRegDate();
        this.commentCount = Long.valueOf(board.getCommentList().size());
    }
}
