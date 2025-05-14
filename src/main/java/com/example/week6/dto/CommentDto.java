package com.example.week6.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long commentId;
    private Long boardId;
    private String content;
    private String writer;

}
