package com.shin.ricu.controller;

import com.shin.ricu.domain.Gallery;
import com.shin.ricu.dto.BoardListWithGalleryDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.service.BoardService;
import com.shin.ricu.service.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
@Log4j2
public class MainController {

    private final GalleryService galleryService;
    private final BoardService boardService;
    @GetMapping("/hello")
    public void hello(Model model, @RequestParam String id, PageRequestDTO pageRequestDTO)
    {
        PageResponseDTO<BoardListWithGalleryDTO> responseDTO = boardService.getBoardListWithGallery(pageRequestDTO, id
                , pageRequestDTO.getType(), pageRequestDTO.getKeyword());
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("galleryDTO", galleryService.getGalleryDTO(id));
    }

    @GetMapping("/index")
    public void indexing()
    {

    }

}
