package com.shin.ricu.controller;

import com.shin.ricu.dto.board.BoardDTOForMembers;
import com.shin.ricu.dto.board.BoardDTOForWriter;
import com.shin.ricu.dto.gallery.GalleryDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.exception.MemberIDIsNotExistException;
import com.shin.ricu.security.dto.MemberSecurityDTO;
import com.shin.ricu.service.BoardService;
import com.shin.ricu.service.GalleryService;
import com.shin.ricu.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
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
        log.info(galleryService.getGalleryDTO(id).getGalleryImageName());
        return galleryService.getGalleryDTO(id);
    }
    @GetMapping("/list")
    public void boardList(PageRequestDTO pageRequestDTO, Model model, @RequestParam String id, String mode)
    {
        log.info(pageRequestDTO);
        if(mode == null) mode = "ALL";
        PageResponseDTO<BoardDTOForMembers> responseDTO;
        responseDTO = boardService.getBoardListWithGallery(pageRequestDTO, id, pageRequestDTO.getType()
                , pageRequestDTO.getKeyword(), mode);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("mode", mode);
    }
    @GetMapping("/write")
    public void write(Model model, @RequestParam String id,
                      @AuthenticationPrincipal MemberSecurityDTO member)
    {
        model.addAttribute("member", member);
    }
    @PostMapping("/write")
    public String writeBoard(@Valid BoardDTOForWriter boardDTOForWriter, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes)
    {
        log.info("board POST register.........");

        if(bindingResult.hasErrors())
        {
            log.info("has error.............");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("id", boardDTOForWriter.getGalleryID());
            return "redirect:/gallery/board/write";
        }
        log.info(boardDTOForWriter);
        Long bno = boardService.writeBoard(boardDTOForWriter);
        galleryService.setGalleryModifiedDate(boardDTOForWriter.getGalleryID(), boardService.readBoard(bno).getRegDate());
        redirectAttributes.addAttribute("id", boardDTOForWriter.getGalleryID());
        return "redirect:/gallery/board/list";
    }

    @GetMapping("/read")
    public void readBoard(Model model, Long bno, PageRequestDTO pageRequestDTO, String mode
            ,@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, RedirectAttributes redirectAttributes)
    {
        if(mode == null) mode = "ALL";
        BoardDTOForMembers boardDTOForMembers = boardService.readBoard(bno);
        if(memberSecurityDTO == null || !memberSecurityDTO.getNickname().equals(boardDTOForMembers.getWriter()))
            boardService.addView(bno);
        log.info(boardDTOForMembers);
        PageResponseDTO<BoardDTOForMembers> responseDTO = boardService.getBoardListWithGallery(pageRequestDTO, boardDTOForMembers.getGalleryID()
                , pageRequestDTO.getType(), pageRequestDTO.getKeyword(), mode);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("dto", boardDTOForMembers);
        model.addAttribute("mode", mode);
        if(memberSecurityDTO != null)
        {
            try
            {
                model.addAttribute("memberDTO", memberService.getMemberByID(memberSecurityDTO.getMemberID()));
            } catch (MemberIDIsNotExistException e)
            {
                redirectAttributes.addFlashAttribute("error", "Member is Not Exist");
            }
        }

    }

    @GetMapping("/modify")
    public String modifyBoard(@RequestParam String id, Model model, Long bno, RedirectAttributes redirectAttributes
            ,@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO)
    {
        BoardDTOForWriter boardDTO = boardService.readBoardForModify(bno);
        if(!memberSecurityDTO.getNickname().equals(boardDTO.getWriter()))
        {
            redirectAttributes.addAttribute("id", id);
            redirectAttributes.addAttribute("bno", bno);
            return "redirect:/gallery/board/read";
        }
        model.addAttribute("dto", boardDTO);
        log.info(boardDTO);
        model.addAttribute("galleryDTO", galleryService.getGalleryDTO(boardDTO.getGalleryID()));
        return "/gallery/board/modify";
    }
    @PostMapping("/remove")
    public String remove(@RequestParam String id, Long bno, RedirectAttributes redirectAttributes)
    {
        log.info("remove post.." + bno + " " + id);
        boardService.removeBoard(bno);
        redirectAttributes.addFlashAttribute("result", "removed");
        redirectAttributes.addAttribute("id", id);
        return "redirect:/gallery/board/list";
    }

    @PostMapping("/modify")
    public String modify(@Valid BoardDTOForWriter boardDTOForWriter, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes)
    {
        log.info("board modify post........." + boardDTOForWriter);
        if(bindingResult.hasErrors())
        {
            log.info("has error.............");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTOForWriter.getBno());
            redirectAttributes.addAttribute("id", boardDTOForWriter.getGalleryID());
            return "redirect:/gallery/board/modify";
        }
        boardService.modifyBoard(boardDTOForWriter);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("id", boardDTOForWriter.getGalleryID());
        redirectAttributes.addAttribute("bno", boardDTOForWriter.getBno());
        return "redirect:/gallery/board/read";
    }

    @PostMapping("/like")
    public String addLike(RedirectAttributes redirectAttributes, String gid, Long bno,
                          @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO)
    {
        redirectAttributes.addAttribute("id", gid);
        redirectAttributes.addAttribute("bno", bno);

        return "redirect:/gallery/board/read";
    }
}
