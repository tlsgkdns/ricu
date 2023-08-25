package com.shin.ricu.service;

import com.shin.ricu.domain.Gallery;
import com.shin.ricu.domain.GalleryImage;
import com.shin.ricu.dto.GalleryCreateDTO;
import com.shin.ricu.dto.GalleryDTO;
import com.shin.ricu.dto.GalleryListAllDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface GalleryService {
    public String createGallery(GalleryCreateDTO galleryCreateDTO);

    public List<Gallery> getAllGalleryList();

    public PageResponseDTO<GalleryListAllDTO> getGalleryListPage(PageRequestDTO pageRequestDTO, String keyword);

    public String getGalleryImage(String id);

    public GalleryDTO getGalleryDTO(String id);
}
