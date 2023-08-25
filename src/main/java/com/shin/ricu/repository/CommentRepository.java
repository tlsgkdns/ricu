package com.shin.ricu.repository;

import com.shin.ricu.domain.Board;
import com.shin.ricu.domain.Comment;
import com.shin.ricu.domain.entityKey.CommentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentKey> {
    Long countByBoard(Board board);
}
