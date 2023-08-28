package com.shin.ricu.controller;

import com.shin.ricu.dto.board.BoardDTO;
import com.shin.ricu.dto.board.BoardListWithGalleryDTO;
import com.shin.ricu.dto.board.BoardModifyDTO;
import com.shin.ricu.dto.gallery.GalleryDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.security.dto.MemberSecurityDTO;
import com.shin.ricu.service.BoardService;
import com.shin.ricu.service.CommentService;
import com.shin.ricu.service.GalleryService;
import com.shin.ricu.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/gallery/board")
@RequiredArgsConstructor
public class BoardController {

    private final GalleryService galleryService;
    private final BoardService boardService;
    private final MemberService memberService;

    @ModelAttribute("galleryDTO")
    public GalleryDTO galleryDTO(@RequestParam String id)
    {
        return galleryService.getGalleryDTO(id);
    }
    @GetMapping("/list")
    public void boardList(PageRequestDTO pageRequestDTO, Model model, @RequestParam String id)
    {
        log.info(pageRequestDTO);
        PageResponseDTO<BoardListWithGalleryDTO> responseDTO = boardService.getBoardListWithGallery(pageRequestDTO, id
                , pageRequestDTO.getType(), pageRequestDTO.getKeyword());
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }
    @GetMapping("/write")
    public void write(Model model, @RequestParam String id,
                      @AuthenticationPrincipal MemberSecurityDTO member)
    {
        model.addAttribute("member", member);
    }
    @PostMapping("/write")
    public String writeBoard(@Valid BoardDTO boardDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes)
    {
        log.info("board POST register.........");

        if(bindingResult.hasErrors())
        {
            log.info("has error.............");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("id", boardDTO.getGalleryID());
            return "redirect:/gallery/board/write";
        }
        log.info(boardDTO);
        Long bno = boardService.writeBoard(boardDTO);
        redirectAttributes.addAttribute("id", boardDTO.getGalleryID());
        return "redirect:/gallery/board/list";
    }

    @GetMapping("/read")
    public void readBoard(Model model, Long bno, PageRequestDTO pageRequestDTO
            ,@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO)
    {
        BoardDTO boardDTO = boardService.readBoard(bno);
        if(memberSecurityDTO == null || !memberSecurityDTO.getNickname().equals(boardDTO.getWriter()))
            boardService.addView(bno);
        log.info(boardDTO);
        PageResponseDTO<BoardListWithGalleryDTO> responseDTO = boardService.getBoardListWithGallery(pageRequestDTO, boardDTO.getGalleryID()
                , pageRequestDTO.getType(), pageRequestDTO.getKeyword());
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("dto", boardDTO);
        if(memberSecurityDTO != null) model.addAttribute("memberDTO", memberService.getMember(memberSecurityDTO.getMemberID()));
    }

    @GetMapping("/modify")
    public void modifyBoard(Model model, Long bno, PageRequestDTO pageRequestDTO)
    {
        BoardModifyDTO boardDTO = boardService.readBoardForModify(bno);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
        model.addAttribute("galleryDTO", galleryService.getGalleryDTO(boardDTO.getGalleryID()));
    }
    @PostMapping("/remove")
    public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes)
    {
        String galleryID = boardDTO.getGalleryID();
        Long bno = boardDTO.getBno();
        log.info("remove post.." + bno + " " + galleryID);
        boardService.removeBoard(bno);
        redirectAttributes.addFlashAttribute("result", "removed");
        redirectAttributes.addAttribute("id", galleryID);
        return "redirect:/gallery/board/list";
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid BoardDTO boardDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes)
    {
        log.info("board modify post........." + boardDTO);
        if(bindingResult.hasErrors())
        {
            log.info("has error.............");
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/gallery/board/modify?" + link;
        }
        boardService.modifyBoard(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("id", boardDTO.getGalleryID());
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/gallery/board/read";
    }
}
