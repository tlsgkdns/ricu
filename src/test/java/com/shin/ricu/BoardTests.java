package com.shin.ricu;

import com.shin.ricu.domain.Board;
import com.shin.ricu.domain.Gallery;
import com.shin.ricu.domain.Member;
import com.shin.ricu.dto.board.BoardListWithGalleryDTO;
import com.shin.ricu.dto.CommentDTO;
import com.shin.ricu.dto.page.PageRequestDTO;
import com.shin.ricu.repository.BoardRepository;
import com.shin.ricu.repository.GalleryRepository;
import com.shin.ricu.repository.MemberRepository;
import com.shin.ricu.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

@SpringBootTest
@Log4j2
public class BoardTests {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    GalleryRepository galleryRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardService boardService;
    @BeforeEach
    public void insertData()
    {
        boardRepository.deleteAll();
        galleryRepository.deleteAll();
        memberRepository.deleteAll();
        Gallery winterGallery = Gallery.builder()
                .galleryID("winter")
                .explanation("Winter is Cold")
                .title("Winter")
                .manager("Snow")
                .build();
        Gallery springGallery = Gallery.builder()
                .galleryID("spring")
                .explanation("Spring is Warm")
                .title("Spring")
                .manager("Flower")
                .build();
        Gallery summerGallery = Gallery.builder()
                .galleryID("summer")
                .explanation("Summer is Hot")
                .title("Summer")
                .manager("Sun")
                .build();
        Gallery autumnGallery = Gallery.builder()
                .galleryID("autumn")
                .explanation("Autumn is Cool")
                .title("Autumn")
                .manager("Fall")
                .build();
        Gallery JanuaryGallery = Gallery.builder()
                .galleryID("January")
                .explanation("January is First")
                .title("January")
                .manager("one")
                .build();
        Gallery FebruaryGallery = Gallery.builder()
                .galleryID("February")
                .explanation("February is Second")
                .title("February")
                .manager("two")
                .build();
        Gallery MarchGallery = Gallery.builder()
                .galleryID("March")
                .explanation("March is third")
                .title("March")
                .manager("three")
                .build();
        Gallery AprilGallery = Gallery.builder()
                .galleryID("April")
                .explanation("April is fourth")
                .title("April")
                .manager("four")
                .build();
        Gallery MayGallery = Gallery.builder()
                .galleryID("May")
                .explanation("May is fifth")
                .title("May")
                .manager("five")
                .build();
        Gallery JuneGallery = Gallery.builder()
                .galleryID("June")
                .explanation("June is sixth")
                .title("June")
                .manager("six")
                .build();
        Gallery JulyGallery = Gallery.builder()
                .galleryID("July")
                .explanation("July is seventh")
                .title("July")
                .manager("seven")
                .build();

        Gallery AugustGallery = Gallery.builder()
                .galleryID("August")
                .explanation("August is eighth")
                .title("August")
                .manager("eight")
                .build();
        Gallery SeptemberGallery = Gallery.builder()
                .galleryID("September")
                .explanation("September is ninth")
                .title("September")
                .manager("nine")
                .build();
        Gallery OctoberGallery = Gallery.builder()
                .galleryID("October")
                .explanation("October is 10th")
                .title("October")
                .manager("ten")
                .build();
        galleryRepository.save(winterGallery);galleryRepository.save(springGallery);
        galleryRepository.save(summerGallery);galleryRepository.save(autumnGallery);
        galleryRepository.save(JanuaryGallery);galleryRepository.save(FebruaryGallery);
        galleryRepository.save(MarchGallery);galleryRepository.save(AprilGallery);
        galleryRepository.save(MayGallery);galleryRepository.save(JuneGallery);
        galleryRepository.save(JulyGallery);galleryRepository.save(AugustGallery);
        galleryRepository.save(SeptemberGallery);galleryRepository.save(OctoberGallery);

        for(Long i = 0L; i < 100L; i++)
        {
            Gallery gallery = winterGallery;
            if(i % 4 == 1) gallery = springGallery;
            if(i % 4 == 2) gallery = summerGallery;
            if(i % 4 == 3) gallery = autumnGallery;
            Member member = Member.builder()
                    .memberID("Member" + i)
                    .nickname("NickName Member " + i)
                    .email("Member"+i+"@")
                    .password("Member"+i+" Password")
                    .build();
            memberRepository.save(member);
            Board board = Board.builder()
                    .bno(i)
                    .content("Hello!!!!!!!!! This is Board " + i)
                    .gallery(gallery)
                    .writer(member)
                    .title("Board " + i)
                    .build();
            log.info(board.getBno() + " is Created!!!!!!!!!!!!!!!!!!!!!!");
            boardRepository.save(board);
        }
    }

    @Test
    public void testBoardSearch()
    {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        Page<BoardListWithGalleryDTO> list = boardRepository.searchBoard(pageRequestDTO.getPageable("bno"), "winter", "", "");
        List<BoardListWithGalleryDTO> boardList = list.getContent().stream().toList();
        log.info(boardList.size() + " is Here!!!!!!!!!!!!!!!!!!!!!!");
        for(int i=0; i < boardList.size(); i++) log.info(boardList.get(i));
    }

    @Test
    public void addCommentTest()
    {
        CommentDTO comment = CommentDTO.builder()
                .bno(1L)
                .commentText("Hello Comment!!!")
                .writer("Sign")
                .build();
        CommentDTO comment2 = CommentDTO.builder()
                .bno(1L)
                .commentText("Hello Comment!!! Again!")
                .writer("Everlasting")
                .build();

        log.info("This is Comment " + comment);
    }

}
