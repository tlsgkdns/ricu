package com.shin.ricu.domain.entityKey;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
public class CommentKey implements Serializable {
    private Long bno;
    private Long rno;

    public CommentKey(Long bno, Long rno)
    {
        this.bno = bno;
        this.rno = rno;
    }

    public CommentKey(Long rno)
    {
        this.rno = rno;
    }
}
