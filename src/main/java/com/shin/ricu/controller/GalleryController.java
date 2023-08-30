package com.shin.ricu.controller;

import com.shin.ricu.dto.gallery.GalleryCreateDTO;
import com.shin.ricu.dto.gallery.GalleryDTO;
import com.shin.ricu.dto.gallery.GalleryListAllDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.exception.GalleryIDExistException;
import com.shin.ricu.security.dto.MemberSecurityDTO;
import com.shin.ricu.service.GalleryService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @Secured("ROLE_USER")
    @GetMapping("/create")
    public void letsCreateGallery(Model model, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO)
    {
        model.addAttribute("managerName", memberSecurityDTO.getNickname());
    }

    @PostMapping("/create")
    public String createGallery(GalleryCreateDTO createDTO, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes)
    {
        log.info("Creating Gallery...................");
        String gid = "";
        try
        {
            gid = galleryService.createGallery(createDTO);
        }
        catch (GalleryIDExistException e)
        {
            redirectAttributes.addFlashAttribute("error", "Already Exist URLName");
            return "redirect:/gallery/create";
        }
        if(bindingResult.hasErrors())
        {
            log.info("has Error while creating Gallery-------------------");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/gallery/create";
        }
        log.info(createDTO);

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

    @GetMapping("/edit")
    public String letsEditGallery(Model model, @RequestParam String id,
                              @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,
                              RedirectAttributes redirectAttributes)
    {
        if(galleryService.getGalleryDTO(id) != null && memberSecurityDTO != null
                && galleryService.getGalleryDTO(id).getManagerID().equals(memberSecurityDTO.getMemberID()))
        {
            model.addAttribute("galleryDTO", galleryService.getGalleryDTO(id));
            return "gallery/edit";
        }
        return "redirect:/gallery/home";
    }

    @PostMapping("/edit")
    public String editGallery(GalleryDTO galleryDTO)
    {
        galleryService.editGalleryInfo(galleryDTO);
        return "redirect:/gallery/home";
    }
}
