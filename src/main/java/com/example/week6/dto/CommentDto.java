package com.example.week6.dto;

import com.example.week6.entity.Comment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long commentId;
    private Long boardId;
    private String boardTitle;
    private String content;
    private String writer;

    public CommentDto(Comment comment) {
        this.commentId = commentId;
        this.boardId = comment.getBoard() != null ? comment.getBoard().getBoardId() : null;
        this.boardTitle = comment.getBoard() != null ? comment.getBoard().getTitle() : null;
        this.content = content;
        this.writer = writer;
    }

}
