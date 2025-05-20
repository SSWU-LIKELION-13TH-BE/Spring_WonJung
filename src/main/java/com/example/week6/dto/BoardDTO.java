package com.example.week6.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {

    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private MultipartFile image;

    @Builder
    public BoardDTO(Long boardId, String title, String content, String writer, MultipartFile image) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.image = image;

    }
}
