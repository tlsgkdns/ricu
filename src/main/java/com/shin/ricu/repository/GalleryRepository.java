package com.shin.ricu.repository;

import com.shin.ricu.domain.Gallery;
import com.shin.ricu.repository.search.GallerySearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GalleryRepository extends JpaRepository<Gallery, String>, GallerySearch {

    @Query("select exists (select g from Gallery g where g.title = :title)")
    public boolean existsByTitle(String title);
}
