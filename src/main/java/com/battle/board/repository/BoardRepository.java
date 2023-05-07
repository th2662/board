package com.battle.board.repository;

import com.battle.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    //해당 키워드가 포함된 글을 검색
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
