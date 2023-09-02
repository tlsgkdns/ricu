package com.shin.ricu.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.shin.ricu.domain.*;
import com.shin.ricu.dto.board.BoardDTOForMembers;
import com.shin.ricu.dto.board.BoardDTOForWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl() {super(Board.class);}

    @Transactional
    public Page<BoardDTOForMembers> searchBoard(Pageable pageable, String galleryID, String types, String keyword, boolean popular)
    {
        QBoard board = QBoard.board;
        QComment comment = QComment.comment;
        QMember member = QMember.member;
        QGallery gallery = QGallery.gallery;
        JPQLQuery<Board> query = from(board);
        query.leftJoin(comment).on(comment.board.eq(board));
        query.innerJoin(member).on(member.memberID.eq(board.writer.memberID));
        query.where(board.gallery.galleryID.eq(galleryID));
        query.groupBy(board);
        log.info("We have query: " + query.fetchCount() + " About: " + galleryID);
        if((types != null && types.length() > 0) && keyword != null)
        {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            log.info(types + " is in HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            for(int i = 0; i < types.length(); i++)
            {
                log.info(types.charAt(i));
                switch (types.charAt(i)) {
                    case 't' -> booleanBuilder.or(board.title.contains(keyword));
                    case 'w' -> booleanBuilder.or(board.writer.nickname.contains(keyword));
                    case 'c' -> booleanBuilder.or(board.content.contains(keyword));
                }
            }
            query.where(booleanBuilder);
        }

        if(popular)
        {
            log.info("Get popular Board.......");
            query.where(board.likeMembers.size().castToNum(Long.class).goe(gallery.popularThreshold));
        }

        List<Board> list = query.fetch();
        log.info(list.size() + " is in Here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! But, ");
        for (Board b: list) {
            log.info(b);
        }
        JPQLQuery<BoardDTOForMembers> dtoQuery = query.select(Projections.bean(BoardDTOForMembers.class,
                board.gallery.galleryID,
                board.bno,
                board.title,
                board.writer.nickname.as("writer"),
                board.likeMembers.size().castToNum(Long.class).as("likeCount"),
                board.regDate,
                board.views,
                comment.count().as("commentCount")
        ));

        this.getQuerydsl().applyPagination(pageable, dtoQuery);
        List<BoardDTOForMembers> boardList = dtoQuery.fetch();
        log.info(boardList.size() + " is DTO in here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return new PageImpl<>(boardList, pageable, dtoQuery.fetchCount());
    }


}
