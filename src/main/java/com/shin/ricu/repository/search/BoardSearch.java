package com.shin.ricu.repository.search;

import com.shin.ricu.domain.Board;
import com.shin.ricu.dto.BoardListWithGalleryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    public Page<BoardListWithGalleryDTO> searchBoard(Pageable pageable, String galleryID, String types, String keyword);
}
