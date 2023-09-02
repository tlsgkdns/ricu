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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/cancelLike/{bno}")
    public Long cancelLike(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, @PathVariable Long bno)
    {
        log.info("Canceling LIKE!");
        if(memberSecurityDTO == null) return -1L;
        return boardService.removeLike(bno, memberSecurityDTO.getMemberID());
    }

    @GetMapping("/{bno}")
    public BoardDTOForMembers readBoard(@PathVariable Long bno){return boardService.readBoard(bno);}

    @GetMapping("/galleryID/{galleryID}/writer/{writer}")
    public PageResponseDTO<BoardDTOForMembers> getBoardListWithWriter(@PathVariable String galleryID
            , @PathVariable String writer, PageRequestDTO pageRequestDTO)
    {
        log.info("GalleryID: " + galleryID + " Writer: " + writer + " PageRequest" + pageRequestDTO);
        PageResponseDTO<BoardDTOForMembers> ret = boardService.getBoardListWithGallery(pageRequestDTO, galleryID, "w", writer, "ALL");
        log.info(ret);
        return ret;
    }
}
