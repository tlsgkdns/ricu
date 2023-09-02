package com.shin.ricu.service;

import com.shin.ricu.domain.Gallery;
import com.shin.ricu.dto.gallery.AutoSearchGalleryDTO;
import com.shin.ricu.dto.gallery.GalleryCreateDTO;
import com.shin.ricu.dto.gallery.GalleryDTO;
import com.shin.ricu.dto.gallery.GalleryListAllDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.exception.GalleryIDExistException;

import java.time.LocalDateTime;
import java.util.List;

public interface GalleryService {
    public String createGallery(GalleryCreateDTO galleryCreateDTO) throws GalleryIDExistException;

    public List<Gallery> getAllGalleryList();

    public PageResponseDTO<GalleryListAllDTO> getGalleryListPage(PageRequestDTO pageRequestDTO, String keyword);

    public String getGalleryImage(String id);

    public GalleryDTO getGalleryDTO(String id);

    public void editGalleryInfo(GalleryDTO galleryDTO);

    public boolean isAvailableURL(String url);

    public boolean isAvailableTitle(String title);

    public void setGalleryModifiedDate(String id, LocalDateTime localDateTime);

    public PageResponseDTO<AutoSearchGalleryDTO> getAutoGalleryList(PageRequestDTO pageRequestDTO, String keyword);
}
