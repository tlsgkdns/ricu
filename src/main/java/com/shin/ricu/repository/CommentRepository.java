package com.shin.ricu.repository;

import com.shin.ricu.domain.Board;
import com.shin.ricu.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countByBoard(Board board);

    @Query("select c from Comment c where c.board.bno = :bno")
    Page<Comment> getCommentListByBoard(Long bno, Pageable pageable);
}
