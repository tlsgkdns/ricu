package com.shin.ricu.service;

import com.shin.ricu.domain.entityKey.CommentKey;
import com.shin.ricu.dto.BoardDTO;
import com.shin.ricu.dto.CommentDTO;

public interface CommentService {
    public CommentKey writeComment(BoardDTO boardDTO, String content);

    public void deleteAllComment();

    public CommentKey registerComment(CommentDTO commentDTO);
}
