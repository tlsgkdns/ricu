package com.shin.ricu.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.BatchSize;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "gallery")
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
    @Builder.Default
    private Long likeThisBoard = 0L;
    @Builder.Default
    private Long views = 0L;

    public void modifyBoard(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
    public void deleteBoard()
    {
        writer = null;
    }

    public Long addViews()
    {
        return ++views;
    }
}
