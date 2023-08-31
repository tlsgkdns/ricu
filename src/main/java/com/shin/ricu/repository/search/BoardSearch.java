package com.shin.ricu.repository.search;

import com.shin.ricu.dto.board.BoardDTOForMembers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    public Page<BoardDTOForMembers> searchBoard(Pageable pageable, String galleryID, String types, String keyword);
}
