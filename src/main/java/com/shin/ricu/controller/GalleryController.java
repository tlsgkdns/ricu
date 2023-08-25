package com.shin.ricu.controller;

import com.shin.ricu.domain.Gallery;
import com.shin.ricu.dto.*;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.security.dto.MemberSecurityDTO;
import com.shin.ricu.service.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @Secured("ROLE_USER")
    @GetMapping("/create")
    public void letsCreateGallery(Model model)
    {
        log.info("Hello!");
        model.addAttribute("managerName", ((MemberSecurityDTO)(SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())).getUsername());
    }

    @PostMapping("/create")
    public String createGallery(GalleryCreateDTO createDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes)
    {
        log.info("Creating Gallery...................");
        if(bindingResult.hasErrors())
        {
            log.info("has Error while creating Gallery-------------------");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/gallery/create";
        }
        log.info(createDTO);
        String gid = galleryService.createGallery(createDTO);
        redirectAttributes.addFlashAttribute("result", gid);
        return "redirect:/gallery/home";
    }

    @GetMapping("/home")
    public void home(Model model, PageRequestDTO pageRequestDTO, String keyword)
    {
        pageRequestDTO.setSize(9);
        PageResponseDTO<GalleryListAllDTO> galleryResponseDTO = galleryService.getGalleryListPage(pageRequestDTO, keyword);
        model.addAttribute("responseDTO", galleryResponseDTO);
    }
}
