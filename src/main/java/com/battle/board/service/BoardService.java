package com.battle.board.service;

import com.battle.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    // 글 작성
    public void write(Board board);

    // 전체 게시글
    public Page<Board> list(Pageable pageable);

    // 글 조회
    public Page<Board> searchList(String serarchKeyword, Pageable pageable);

    // 글 상세페이지
    public Board view(Integer id);

    // 글 삭제
    public void delete(Integer id);

}
