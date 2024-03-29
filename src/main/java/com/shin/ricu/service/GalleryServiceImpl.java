package com.shin.ricu.service;

import com.shin.ricu.domain.Gallery;
import com.shin.ricu.domain.image.GalleryImage;
import com.shin.ricu.dto.gallery.AutoSearchGalleryDTO;
import com.shin.ricu.dto.gallery.GalleryCreateDTO;
import com.shin.ricu.dto.gallery.GalleryDTO;
import com.shin.ricu.dto.gallery.GalleryListAllDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.exception.GalleryIDExistException;
import com.shin.ricu.repository.GalleryRepository;
import com.shin.ricu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class GalleryServiceImpl implements GalleryService{
    private final ModelMapper modelMapper;
    private final GalleryRepository galleryRepository;
    private final MemberRepository memberRepository;
    @Override
    public String createGallery(GalleryCreateDTO galleryCreateDTO) throws GalleryIDExistException
    {
        Optional<Gallery> tmp = galleryRepository.findById(galleryCreateDTO.getGalleryID());
        if(tmp != Optional.<Gallery>empty())
        {
            throw new GalleryIDExistException();
        }
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

    @Override
    public void editGalleryInfo(GalleryDTO galleryDTO) {
        log.info(galleryDTO);
        Gallery gallery = galleryRepository.findById(galleryDTO.getGalleryID()).orElseThrow();
        gallery.changeExplanation(galleryDTO.getExplanation());
        gallery.setPopularThreshold(galleryDTO.getPopularThreshold());
        if(galleryDTO.getGalleryImageName() != null)
        {
            String[] fileName = galleryDTO.getGalleryImageName().split("_");
            if(fileName.length == 2)
                gallery.setImage(fileName[0], fileName[1]);
        }
        galleryRepository.save(gallery);
    }

    @Override
    public boolean isAvailableURL(String url) {
        if(url.length() < 3 || url.length() > 20 || !url.matches("[0-9|a-z|A-Z]*")) return false;
        return !galleryRepository.existsById(url);
    }

    @Override
    public boolean isAvailableTitle(String title) {
        if(title.length() < 3 || title.length() > 20 || !title.matches("[0-9|a-z|A-Z]*")) return false;
        return !galleryRepository.existsByTitle(title);
    }

    @Override
    public void setGalleryModifiedDate(String id, LocalDateTime localDateTime) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow();
        gallery.setLastModifiedDate(localDateTime);
        log.info(localDateTime);
        galleryRepository.save(gallery);
    }

    @Override
    public PageResponseDTO<AutoSearchGalleryDTO> getAutoGalleryList(PageRequestDTO pageRequestDTO, String keyword) {
        Page<AutoSearchGalleryDTO> list = galleryRepository.searchGalleryForAuto(pageRequestDTO.getPageable(), keyword);

        return PageResponseDTO.<AutoSearchGalleryDTO>withAll()
                .dtoList(list.getContent())
                .pageRequestDTO(pageRequestDTO)
                .total((int)list.getTotalElements())
                .build();
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
                .title(galleryCreateDTO.getTitle())
                .explanation(galleryCreateDTO.getExplanation())
                .manager(memberRepository.getMemberByNickName(galleryCreateDTO.getManager()))
                .build();
        if(galleryCreateDTO.getGalleryImageName() != null)
        {
            String[] arr = galleryCreateDTO.getGalleryImageName().split("_");
            gallery.setImage(arr[0], arr[1]);
            log.info(gallery.getGalleryImage());
        }
        return gallery;
    }

    private GalleryDTO entityToDTO(Gallery gallery)
    {
        GalleryDTO galleryDTO = modelMapper.map(gallery, GalleryDTO.class);
        galleryDTO.setManagerID(gallery.getManager().getMemberID());
        galleryDTO.setManagerNickname(gallery.getManager().getNickname());
        if(gallery.getGalleryImage() != null)
            galleryDTO.setGalleryImageName(gallery.getGalleryImage().getLink());
        return galleryDTO;
    }
}
