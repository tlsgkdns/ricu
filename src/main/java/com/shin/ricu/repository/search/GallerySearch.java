package com.shin.ricu.repository.search;

import com.shin.ricu.dto.gallery.AutoSearchGalleryDTO;
import com.shin.ricu.dto.gallery.GalleryListAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GallerySearch {

    public Page<GalleryListAllDTO> searchGallery(Pageable pageable, String keyword);

    public Page<AutoSearchGalleryDTO> searchGalleryForAuto(Pageable pageable, String keyword);
}
