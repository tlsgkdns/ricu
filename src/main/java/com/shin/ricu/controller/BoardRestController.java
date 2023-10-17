package com.shin.ricu.controller;


import com.shin.ricu.dto.board.BoardDTOForMembers;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.repository.BoardRepository;
import com.shin.ricu.security.dto.MemberSecurityDTO;
import com.shin.ricu.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boardRest")
@RequiredArgsConstructor
@Log4j2
public class BoardRestController {

    private final BoardService boardService;
    @RequestMapping("/like/{bno}")
    public Long addLike(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, @PathVariable Long bno)
    {
        log.info("add LIKE1");
        if(memberSecurityDTO == null) return -1L;
        log.info("add Like2!");
        Long ret =  boardService.addLike(bno, memberSecurityDTO.getMemberID());
        log.info("NO. " + ret);
        return ret;
    }

    @DeleteMapping("/like/{bno}")
    public Long cancelLike(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, @PathVariable Long bno)
    {
        log.info("Canceling LIKE!");
        if(memberSecurityDTO == null) return -1L;
        return boardService.removeLike(bno, memberSecurityDTO.getMemberID());
    }

    @GetMapping("/{bno}")
    public BoardDTOForMembers readBoard(@PathVariable Long bno){return boardService.readBoard(bno);}

    @GetMapping("/gallery-id/{title}/{writer}")
    public PageResponseDTO<BoardDTOForMembers> getBoardListWithWriter(@PathVariable String title
            , @PathVariable String writer, PageRequestDTO pageRequestDTO)
    {
        log.info("GalleryID: " + title + " Writer: " + writer + " PageRequest" + pageRequestDTO);
        PageResponseDTO<BoardDTOForMembers> ret =
                boardService.getBoardListWithGalleryTitle(pageRequestDTO, title, "w", writer, "ALL");
        log.info(ret);
        return ret;
    }
}
