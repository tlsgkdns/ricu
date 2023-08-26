package com.shin.ricu.service;

import com.shin.ricu.dto.BoardDTO;
import com.shin.ricu.dto.CommentDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;

public interface CommentService {
    public Long writeComment(CommentDTO commentDTO);

    public void deleteAllComment();

    public Long modifyComment(CommentDTO commentDTO);

    public CommentDTO readComment(Long commentID);

    public void removeComment(Long commentID);

    PageResponseDTO<CommentDTO> getCommentListByBoard(Long bno, PageRequestDTO pageRequestDTO);
}
