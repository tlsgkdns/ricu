package com.shin.ricu.service;

import com.shin.ricu.dto.board.BoardDTOForMembers;
import com.shin.ricu.dto.board.BoardDTOForWriter;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;

public interface BoardService {

    public Long writeBoard(BoardDTOForWriter boardDTOForMembers);

    public PageResponseDTO<BoardDTOForMembers> getBoardListWithGallery(PageRequestDTO pageRequestDTO
            , String galleryID, String types, String keyword, String mode);

    public BoardDTOForMembers readBoard(Long bno);
    public void removeBoard(Long bno);

    public void modifyBoard(BoardDTOForWriter boardDTOForWriter);

    public BoardDTOForWriter readBoardForModify(Long bno);

    public Long addView(Long bno);

    public Long addLike(Long bno, String memberID);

    public Long removeLike(Long bno, String memberID);
}
