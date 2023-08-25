package com.shin.ricu.repository;

import com.shin.ricu.domain.Gallery;
import com.shin.ricu.dto.GalleryListAllDTO;
import com.shin.ricu.repository.search.GallerySearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, String>, GallerySearch {

}
