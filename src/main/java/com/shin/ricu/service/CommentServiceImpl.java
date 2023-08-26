package com.shin.ricu.service;

import com.shin.ricu.domain.Board;
import com.shin.ricu.domain.Comment;
import com.shin.ricu.dto.BoardDTO;
import com.shin.ricu.dto.CommentDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardService boardService;
    private final ModelMapper modelMapper;
    @Override
    public Long writeComment(CommentDTO commentDTO)
    {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        log.info(comment);
        Long commentID = commentRepository.save(comment).getCommentID();
        return commentID;
    }

    @Override
    public void deleteAllComment() {
        commentRepository.deleteAll();
    }

    @Override
    public Long modifyComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getCommentID()).orElseThrow();
        comment.changeText(commentDTO.getCommentText());
        Long commentID = commentRepository.save(comment).getCommentID();
        return commentID;
    }

    @Override
    public CommentDTO readComment(Long commentID) {
        Comment comment = commentRepository.findById(commentID).orElseThrow();
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public void removeComment(Long commentID) {
        commentRepository.deleteById(commentID);
    }

    @Override
    public PageResponseDTO<CommentDTO> getCommentListByBoard(Long bno, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(), Sort.by("commentID").ascending());
        Page<Comment> result = commentRepository.getCommentListByBoard(bno, pageable);
        List<CommentDTO> dtoList = result.getContent().stream().map(reply -> modelMapper.map(reply, CommentDTO.class))
                .collect(Collectors.toList());

        for(CommentDTO dto : dtoList)
            log.info(dto +" Comment is in now here!!!!!!!!!!!!!!");
        return PageResponseDTO.<CommentDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }


}
