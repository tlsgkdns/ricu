package com.shin.ricu.repository;

import com.shin.ricu.domain.Board;
import com.shin.ricu.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
}
