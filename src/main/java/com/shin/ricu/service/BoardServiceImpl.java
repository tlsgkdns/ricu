package com.shin.ricu.service;

import com.shin.ricu.domain.Board;
import com.shin.ricu.domain.Comment;
import com.shin.ricu.dto.*;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.dto.page.PageResponseDTO;
import com.shin.ricu.repository.BoardRepository;
import com.shin.ricu.repository.GalleryRepository;
import com.shin.ricu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final GalleryRepository galleryRepository;
    private final ModelMapper modelMapper;
    @Override
    public Long writeBoard(BoardDTO boardDTO)
    {
        log.info(boardDTO + "saved!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Board board = dtoToEntity(boardDTO);
        log.info("Writing Board " + board + "..............................................");
        boardRepository.save(board);
        return board.getBno();
    }

    @Override
    public PageResponseDTO<BoardListWithGalleryDTO> getBoardListWithGallery(PageRequestDTO pageRequestDTO, String galleryID, String types, String keyword)
    {
        Page<BoardListWithGalleryDTO> list = boardRepository.searchBoard(pageRequestDTO.getPageable("bno"), galleryID, types, keyword);

        return PageResponseDTO.<BoardListWithGalleryDTO>withAll()
                .dtoList(list.getContent())
                .pageRequestDTO(pageRequestDTO)
                .total((int)list.getTotalElements())
                .build();
    }

    @Transactional
    @Override
    public BoardDTO readBoard(Long bno)
    {
        Optional<Board> optionalBoard = boardRepository.findById(bno);
        Board board = optionalBoard.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        boardDTO.setWriter(board.getWriter().getNickname());
        return boardDTO;
    }

    @Override
    public void removeBoard(Long bno) {
        boardRepository.getById(bno).deleteBoard();
        boardRepository.deleteById(bno);
    }

    @Override
    public void modifyBoard(BoardDTO boardDTO) {

        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        Board board = result.orElseThrow();

        board.modifyBoard(boardDTO.getTitle(), boardDTO.getContent());

        boardRepository.save(board);
    }

    @Override
    public BoardModifyDTO readBoardForModify(Long bno) {
        Board board = boardRepository.getById(bno);
        BoardModifyDTO boardModifyDTO = modelMapper.map(board, BoardModifyDTO.class);
        boardModifyDTO.setWriter(board.getWriter().getNickname());
        return boardModifyDTO;
    }

    public Board dtoToEntity(BoardDTO boardDTO)
    {
        Board board = Board.builder()
                .writer(memberRepository.getById(boardDTO.getWriter()))
                .content(boardDTO.getContent())
                .title(boardDTO.getTitle())
                .bno(boardDTO.getBno())
                .gallery(galleryRepository.getById(boardDTO.getGalleryID()))
                .build();
        return board;
    }
}
