package com.battle.board.DTO;

import lombok.Getter;

@Getter
public class BoardDto {
    private Integer id;
    private String title;
    private String content;

    public BoardDto(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
