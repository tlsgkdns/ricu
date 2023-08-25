package com.shin.ricu.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.shin.ricu.domain.*;
import com.shin.ricu.dto.BoardListWithGalleryDTO;
import com.shin.ricu.dto.QBoardListWithGalleryDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl() {super(Board.class);}

    @Transactional
    public Page<BoardListWithGalleryDTO> searchBoard(Pageable pageable, String galleryID, String types, String keyword)
    {
        QBoard board = QBoard.board;
        QGallery gallery = QGallery.gallery;
        QComment comment = QComment.comment;
        JPQLQuery<Board> query = from(board);

        query.leftJoin(gallery).on(board.gallery.eq(gallery));
        query.where(board.gallery.galleryID.eq(galleryID));
        log.info(types + " is my TYPE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        if((types != null && types.length() > 0) && keyword != null)
        {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            log.info(types + "is in HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            for(int i = 0; i < types.length(); i++)
            {
                log.info(types.charAt(i));
                switch (types.charAt(i)) {
                    case 't' -> booleanBuilder.or(board.title.contains(keyword));
                    case 'w' -> booleanBuilder.or(board.writer.nickname.contains(keyword));
                    case 'c' -> booleanBuilder.or(board.content.contains(keyword));
                }
                query.where(booleanBuilder);
            }
        }

        getQuerydsl().applyPagination(pageable, query);
        List<Board> list = query.fetch();
        log.info(list.size() + " is in Here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! But, ");
        List<BoardListWithGalleryDTO> boardList = query.select(new QBoardListWithGalleryDTO(board)).from(board).fetch();
        log.info(boardList.size() + " is DTO in here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return new PageImpl<>(boardList, pageable, query.fetchCount());
    }


}
