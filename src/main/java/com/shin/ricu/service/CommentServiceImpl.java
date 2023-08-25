package com.shin.ricu.service;

import com.shin.ricu.domain.Board;
import com.shin.ricu.domain.Comment;
import com.shin.ricu.domain.entityKey.CommentKey;
import com.shin.ricu.dto.BoardDTO;
import com.shin.ricu.dto.CommentDTO;
import com.shin.ricu.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardService boardService;
    private final ModelMapper modelMapper;
    @Override
    public CommentKey writeComment(BoardDTO boardDTO, String content)
    {
        Board board = modelMapper.map(boardDTO, Board.class);
        Long cnt = commentRepository.countByBoard(board);
        Comment comment = Comment.builder().board(board).content(content).build();
        comment.setCommentKey(board, cnt);
        log.info(comment);
        commentRepository.save(comment);
        return comment.getCommentKey();
    }

    @Override
    public void deleteAllComment() {
        commentRepository.deleteAll();
    }

    @Override
    public CommentKey registerComment(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        CommentKey commentKey = commentRepository.save(comment).getCommentKey();
        return commentKey;
    }
}
