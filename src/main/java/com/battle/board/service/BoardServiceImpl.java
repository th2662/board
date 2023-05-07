package com.battle.board.service;

import com.battle.board.entity.Board;
import com.battle.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardRepository boardRepository;


    // 글 작성
    public void write(Board board) {
        boardRepository.save(board);
    }

    // 전체 게시글
    public Page<Board> list(Pageable pageable) {

        return boardRepository.findAll(pageable);

    }

    // 글 조회
    public Page<Board> searchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);

    }

    // 글 상세페이지
    public Board view(Integer id) {
        return boardRepository.findById(id).get();
    }

    // 글 삭제
    public void delete(Integer id) {
        boardRepository.deleteById(id);
    }






}
