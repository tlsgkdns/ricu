package com.shin.ricu.service;

import com.shin.ricu.dto.board.BoardDTO;
import com.shin.ricu.dto.board.BoardListWithGalleryDTO;
import com.shin.ricu.dto.board.BoardModifyDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;

public interface BoardService {

    public Long writeBoard(BoardDTO boardDTO);

    public PageResponseDTO<BoardListWithGalleryDTO> getBoardListWithGallery(PageRequestDTO pageRequestDTO, String galleryID, String types, String keyword);

    public BoardDTO readBoard(Long bno);
    public void removeBoard(Long bno);

    public void modifyBoard(BoardDTO boardDTO);

    public BoardModifyDTO readBoardForModify(Long bno);

    public Long addView(Long bno);
}
