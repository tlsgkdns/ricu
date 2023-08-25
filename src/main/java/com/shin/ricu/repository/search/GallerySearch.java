package com.shin.ricu.repository.search;

import com.shin.ricu.dto.BoardListWithGalleryDTO;
import com.shin.ricu.dto.GalleryListAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

public interface GallerySearch {

    public Page<GalleryListAllDTO> searchGallery(Pageable pageable, String keyword);

}
