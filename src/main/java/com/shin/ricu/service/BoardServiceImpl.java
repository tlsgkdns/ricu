package com.shin.ricu.service;

import com.shin.ricu.domain.Board;
import com.shin.ricu.dto.board.BoardDTOForMembers;
import com.shin.ricu.dto.board.BoardDTOForWriter;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    public Long writeBoard(BoardDTOForWriter boardDTOForWriter)
    {
        log.info(boardDTOForWriter + "saved!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Board board = Board.builder()
                .writer(memberRepository.findById(boardDTOForWriter.getWriter()).orElseThrow())
                .content(boardDTOForWriter.getContent())
                .title(boardDTOForWriter.getTitle())
                .bno(boardDTOForWriter.getBno())
                .gallery(galleryRepository.findById(boardDTOForWriter.getGalleryID()).orElseThrow())
                .build();
        log.info("Writing Board " + board + "..............................................");
        boardRepository.save(board);
        return board.getBno();
    }

    @Override
    public PageResponseDTO<BoardDTOForMembers> getBoardListWithGallery(PageRequestDTO pageRequestDTO, String galleryID
            , String types, String keyword, String mode)
    {
        Page<BoardDTOForMembers> list = boardRepository.searchBoard(pageRequestDTO.getPageable("bno")
                , galleryID, types, keyword, mode != null && mode.equals("Popular"));

        return PageResponseDTO.<BoardDTOForMembers>withAll()
                .dtoList(list.getContent())
                .pageRequestDTO(pageRequestDTO)
                .total((int)list.getTotalElements())
                .build();
    }

    @Transactional
    @Override
    public BoardDTOForMembers readBoard(Long bno)
    {
        Optional<Board> optionalBoard = boardRepository.findById(bno);
        Board board = optionalBoard.orElseThrow();
        BoardDTOForMembers boardDTOForMembers = boardMemberDTOToBoard(board);
        boardDTOForMembers.setWriter(board.getWriter().getNickname());
        return boardDTOForMembers;
    }

    @Override
    public void removeBoard(Long bno) {
        boardRepository.findById(bno).orElseThrow().deleteBoard();
        boardRepository.deleteById(bno);
    }

    @Override
    public void modifyBoard(BoardDTOForWriter boardDTOForWriter) {

        Optional<Board> result = boardRepository.findById(boardDTOForWriter.getBno());
        Board board = result.orElseThrow();
        board.modifyBoard(boardDTOForWriter.getTitle(), boardDTOForWriter.getContent());
        boardRepository.save(board);
    }
    @Override
    public Long addView(Long bno) {
        Board board = boardRepository.findById(bno).orElseThrow();
        return board.addViews();
    }

    @Override
    public BoardDTOForWriter readBoardForModify(Long bno) {
        Board board = boardRepository.findById(bno).orElseThrow();
        BoardDTOForWriter boardDTOForWriter = modelMapper.map(board, BoardDTOForWriter.class);
        boardDTOForWriter.setWriter(board.getWriter().getNickname());
        return boardDTOForWriter;
    }

    @Override
    public Long addLike(Long bno, String memberID) {
        Board board = boardRepository.findById(bno).orElseThrow();
        log.info("Is Exist " + memberID);
        if(board.getLikeMembers().contains(memberID)) return -1L;
        log.info("We are now insert Like " + memberID);
        board.addLikeMember(memberID);
        log.info(board.getLikeMembers().size() + " Size!!!!!!!!!");
        boardRepository.save(board);
        return Long.valueOf(board.getLikeMembers().size());
    }

    @Override
    public Long removeLike(Long bno, String memberID) {
        Board board = boardRepository.findById(bno).orElseThrow();
        log.info("Canceling Like... " + memberID);
        if(!board.getLikeMembers().contains(memberID)) return -1L;
        board.removeLike(memberID);
        log.info(board.getLikeMembers().size() + "In NOW!");
        boardRepository.save(board);
        return Long.valueOf(board.getLikeMembers().size());
    }

    public BoardDTOForMembers boardMemberDTOToBoard(Board board)
    {
        BoardDTOForMembers dto = BoardDTOForMembers.builder()
                .title(board.getTitle())
                .views(board.getViews())
                .writer(board.getWriter().getNickname())
                .likeCount(Long.valueOf(board.getLikeMembers().size()))
                .content(board.getContent())
                .bno(board.getBno())
                .galleryID(board.getGallery().getGalleryID())
                .regDate(board.getRegDate())
                .build();

        return dto;
    }
}
