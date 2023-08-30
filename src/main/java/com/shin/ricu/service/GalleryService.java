package com.shin.ricu.service;

import com.shin.ricu.domain.Gallery;
import com.shin.ricu.dto.gallery.GalleryCreateDTO;
import com.shin.ricu.dto.gallery.GalleryDTO;
import com.shin.ricu.dto.gallery.GalleryListAllDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.exception.GalleryIDExistException;

import java.util.List;

public interface GalleryService {
    public String createGallery(GalleryCreateDTO galleryCreateDTO) throws GalleryIDExistException;

    public List<Gallery> getAllGalleryList();

    public PageResponseDTO<GalleryListAllDTO> getGalleryListPage(PageRequestDTO pageRequestDTO, String keyword);

    public String getGalleryImage(String id);

    public GalleryDTO getGalleryDTO(String id);

    public void editGalleryInfo(GalleryDTO galleryDTO);

    public boolean isExistByURL(String url);

    public boolean isExistByTitle(String title);
}
