package com.shin.ricu.domain;

import com.shin.ricu.domain.entityKey.CommentKey;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"gallery", "commentList"})
@Log4j2
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String content;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Member writer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gallery_id", referencedColumnName = "galleryID")
    private Gallery gallery;
    @OneToMany(mappedBy = "board", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    @BatchSize(size = 20)
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();
    @Builder.Default
    private Long like = 0L;
    @Builder.Default
    private Long views = 0L;

    public CommentKey addComment(String content, String writer)
    {
      Comment comment = Comment.builder()
              .commentKey(new CommentKey(bno, Long.valueOf(commentList.size())))
              .writer(writer)
              .content(content)
              .board(this)
              .build();
      log.info("Hello Comment !!!!!!!!!!! " + comment);
      commentList.add(comment);
      return comment.getCommentKey();
    }

    public void modifyBoard(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
    public void deleteBoard()
    {
        writer = null;
    }
}
