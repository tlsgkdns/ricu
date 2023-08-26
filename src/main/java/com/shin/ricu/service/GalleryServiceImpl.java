package com.shin.ricu.service;

import com.shin.ricu.domain.Gallery;
import com.shin.ricu.domain.GalleryImage;
import com.shin.ricu.dto.*;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class GalleryServiceImpl implements GalleryService{
    private final ModelMapper modelMapper;
    private final GalleryRepository galleryRepository;
    @Override
    public String createGallery(GalleryCreateDTO galleryCreateDTO)
    {
        Gallery gallery = createDTOToEntity(galleryCreateDTO);
        String id = galleryRepository.save(gallery).getGalleryID();
        return id;
    }

    @Override
    public List<Gallery> getAllGalleryList() {
        return galleryRepository.findAll();
    }

    @Override
    public PageResponseDTO<GalleryListAllDTO> getGalleryListPage(PageRequestDTO pageRequestDTO, String keyword) {
        Page<GalleryListAllDTO> list = galleryRepository.searchGallery(pageRequestDTO.getPageable(), keyword);

        return PageResponseDTO.<GalleryListAllDTO>withAll()
                .dtoList(list.getContent())
                .pageRequestDTO(pageRequestDTO)
                .total((int)list.getTotalElements())
                .build();
    }

    @Override
    public String getGalleryImage(String id)
    {
        GalleryImage image = galleryRepository.findById(id).orElseThrow().getGalleryImage();
        if(image == null) return null;
        return image.getLink();
    }

    @Override
    public GalleryDTO getGalleryDTO(String id)
    {
        log.info("Search for........... " + id);
        Gallery gallery = galleryRepository.findById(id).orElseThrow();
        return entityToDTO(gallery);
    }

    //@Override
    /*public PageResponseDTO<GalleryListAllDTO> getAllGalleryList(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<GalleryListAllDTO> g = PageResponseDTO.<GalleryListAllDTO>withAll().
                pageRequestDTO(pageRequestDTO)
                .
    }*/

    private Gallery createDTOToEntity(GalleryCreateDTO galleryCreateDTO)
    {

        Gallery gallery = Gallery.builder()
                .galleryID(galleryCreateDTO.getGalleryID())
                .manager(galleryCreateDTO.getManager())
                .title(galleryCreateDTO.getTitle())
                .explanation(galleryCreateDTO.getExplanation())
                .build();
        if(galleryCreateDTO.getGalleryImage() != null)
        {
            String[] arr = galleryCreateDTO.getGalleryImage().split("_");
            gallery.setImage(arr[0], arr[1]);
            log.info(gallery.getGalleryImage());
        }
        return gallery;
    }

    private GalleryDTO entityToDTO(Gallery gallery)
    {
        GalleryDTO galleryDTO = modelMapper.map(gallery, GalleryDTO.class);
        if(gallery.getGalleryImage() != null)
            galleryDTO.setGalleryImageName(gallery.getGalleryImage().getLink());
        return galleryDTO;
    }
}
