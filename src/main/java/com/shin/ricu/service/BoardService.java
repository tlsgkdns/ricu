package com.shin.ricu.service;

import com.shin.ricu.domain.Board;
import com.shin.ricu.domain.entityKey.CommentKey;
import com.shin.ricu.dto.*;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;

public interface BoardService {

    public Long writeBoard(BoardDTO boardDTO);

    public PageResponseDTO<BoardListWithGalleryDTO> getBoardListWithGallery(PageRequestDTO pageRequestDTO, String galleryID, String types, String keyword);

    public BoardDTO readBoard(Long bno);
    public void removeBoard(Long bno);

    public CommentKey addComment(CommentDTO commentDTO);

    public void modifyBoard(BoardDTO boardDTO);

    public BoardModifyDTO readBoardForModify(Long bno);
}
