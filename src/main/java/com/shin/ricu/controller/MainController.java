package com.shin.ricu.controller;

import com.shin.ricu.dto.board.BoardDTOForMembers;
import com.shin.ricu.dto.board.BoardDTOForWriter;
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

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Log4j2
public class MainController {
    @GetMapping("/")
    public String indexing()
    {
        return "redirect:/gallery/home";
    }

}
