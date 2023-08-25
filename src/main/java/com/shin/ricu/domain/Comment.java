package com.shin.ricu.domain;


import com.shin.ricu.domain.entityKey.CommentKey;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "commentKey")
public class Comment {

    @EmbeddedId
    private CommentKey commentKey;

    @MapsId("bno")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private Board board;

    private String content;

    private String writer;
    public void setCommentKey(Board board, Long rno)
    {
        this.board = board;
        commentKey = new CommentKey(rno);
    }
}
