package com.shin.ricu.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.shin.ricu.domain.Gallery;
import com.shin.ricu.domain.QGallery;
import com.shin.ricu.dto.gallery.GalleryListAllDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class GallerySearchImpl extends QuerydslRepositorySupport implements GallerySearch {
    public GallerySearchImpl() {super(Gallery.class); }
    @Override
    public Page<GalleryListAllDTO> searchGallery(Pageable pageable, String keyword) {
        QGallery gallery = QGallery.gallery;
        JPQLQuery<Gallery> query = from(gallery);
        if(keyword != null && keyword.length() > 0)
        {
            BooleanBuilder builder = new BooleanBuilder();
            builder.or(gallery.title.contains(keyword));
            builder.or(gallery.explanation.contains(keyword));
            query.where(builder);
        }
        getQuerydsl().applyPagination(pageable, query);
        List<Gallery> galleries = query.fetch();
        log.info(galleries.size() + " galleries......................");
        JPQLQuery<GalleryListAllDTO> dtoQuery = query.select(Projections.bean(GalleryListAllDTO.class,
                gallery.galleryID,
                gallery.title,
                gallery.manager.memberID.as("managerMemberID"),
                gallery.lastModifiedDate
        ));
        List<GalleryListAllDTO> list = dtoQuery.fetch();
        for(int i = 0; i < list.size(); i++)
        {
            if(galleries.get(i).getGalleryImage() == null) continue;
            log.info("Hello Image??????????????????????????????????????????????????");
            list.get(i).setGalleryImageName(galleries.get(i).getGalleryImage().getLink());
            log.info(list.get(i).getGalleryImageName());
        }
        log.info(list.size() + " is in NOW!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return new PageImpl<>(list, pageable, dtoQuery.fetchCount());
    }
}
