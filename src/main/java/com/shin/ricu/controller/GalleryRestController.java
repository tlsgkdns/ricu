package com.shin.ricu.controller;


import com.shin.ricu.dto.board.BoardDTOForMembers;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.service.GalleryService;
import com.shin.ricu.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/galleryRest")
@RequiredArgsConstructor
public class GalleryRestController {
    private final GalleryService galleryService;
    @GetMapping("/urlname/{urlname}")
    public boolean isExistByID(@PathVariable String urlname)
    {
        return galleryService.isExistByURL(urlname);
    }

    @GetMapping("/title/{title}")
    public boolean isExistByNickname(@PathVariable String title)
    {
        return galleryService.isExistByTitle(title);
    }
}
