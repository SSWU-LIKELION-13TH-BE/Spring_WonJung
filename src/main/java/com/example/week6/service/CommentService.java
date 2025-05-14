package com.example.week6.service;
import com.example.week6.dto.CommentDto;
import com.example.week6.entity.Board;
import com.example.week6.entity.Comment;
import com.example.week6.entity.Member;
import com.example.week6.repository.BoardRepository;
import com.example.week6.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 작성
    public void postComment(CommentDto commentDto) {
        // 1. 게시글 조회
        Board board = boardRepository.findById(commentDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        // 2. 댓글 생성
        Comment comment = Comment.builder()
                .board(board)
                .content(commentDto.getContent())
                .writer(commentDto.getWriter())
                .createdAt(LocalDate.now())
                .build();

        commentRepository.save(comment);
    }
}
