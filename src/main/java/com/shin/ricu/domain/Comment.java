package com.shin.ricu.domain;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="Comment", indexes = {
        @Index(name="idx_comment_board_bno", columnList = "board_bno")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String commentText;

    private String writer;
    public void changeText(String text)
    {
        commentText = text;
    }
}
