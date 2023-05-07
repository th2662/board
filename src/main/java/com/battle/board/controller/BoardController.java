package com.battle.board.controller;

import com.battle.board.entity.Board;
import com.battle.board.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    BoardServiceImpl boardService;

    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardwrite";
    }

    @PostMapping({"/board/writepro"})
    public String boardWritePro(Board board) {
        boardService.write(board);

        return "redirect:/board/list";

    }

    // 페이징 없이 리스트보기
//    @GetMapping("/board/list")
//    public String boardList(Model model) {
//
//        model.addAttribute("list", boardService.list());
//        return "boardList";
//    }

    // 페이징 구현한 리스트
    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
                            Pageable pageable, String searchKeyword) {

        Page<Board> list = null;

        // 검색을 했을때와 안했을 때를 구분
        if (searchKeyword == null) {
            list = boardService.list(pageable);
        } else {
            list = boardService.searchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber()+1; // 0부터 시작하기때문에
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardList";
    }



    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {
        model.addAttribute("board", boardService.view(id));
        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", boardService.view(id));
        return "boardModify";
    }

    @PostMapping({"/board/update/{id}"})
    public String boardUpdate(@PathVariable("id") Integer id, Board board) {
        Board boardTemp = boardService.view(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardService.write(boardTemp);

        return "redirect:/board/list";
    }
}
