package com.example.week6.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {

    private Long boardId;
    private String title;
    private String content;
    private String writer;

    @Builder
    public BoardDTO(Long boardId, String title, String content, String writer) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;

    }
}
