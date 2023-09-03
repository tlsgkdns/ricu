package com.shin.ricu.controller;

import com.shin.ricu.domain.Comment;
import com.shin.ricu.dto.CommentDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequestMapping("/comments")
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> writerComment(@Valid @RequestBody CommentDTO commentDTO,
                                      BindingResult bindingResult) throws BindException
    {
        log.info(commentDTO);
        if(bindingResult.hasErrors())
        {
            throw new BindException(bindingResult);
        }
        Map<String, Long> resultMap = new HashMap<>();
        Long rno = commentService.writeComment(commentDTO);
        resultMap.put("rno", rno);
        return resultMap;
    }

    @GetMapping(value = "/list/{bno}")
    public PageResponseDTO<CommentDTO> getCommentList(@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO)
    {
        log.info(pageRequestDTO);
        PageResponseDTO<CommentDTO> responseDTO = commentService.getCommentListByBoard(bno, pageRequestDTO);
        return responseDTO;
    }
    @GetMapping("/{rno}")
    public CommentDTO getCommentDTO(@PathVariable("rno") Long commentID)
    {
        CommentDTO commentDTO = commentService.readComment(commentID);

        return commentDTO;
    }

    @DeleteMapping("/{rno}")
    public Map<String, Long> removeComment(@PathVariable("rno") Long commentID)
    {
        commentService.removeComment(commentID);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", commentID);
        return resultMap;
    }

    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modifyComment(@PathVariable("rno")Long rno, @RequestBody CommentDTO commentDTO)
    {
        commentDTO.setCommentID(rno);
        commentService.modifyComment(commentDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }

    @GetMapping(value = "/count/{bno}")
    public Long getCommentCount(@PathVariable("bno") Long bno)
    {
        log.info("Get Count " + bno);
        Long count = commentService.getCommentCount(bno);
        return count;
    }
}
