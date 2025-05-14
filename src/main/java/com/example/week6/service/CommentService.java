package com.example.week6.service;
import com.example.week6.dto.CommentDto;
import com.example.week6.entity.Board;
import com.example.week6.entity.Comment;
import com.example.week6.repository.BoardRepository;
import com.example.week6.repository.CommentRepository;
import jakarta.transaction.Transactional;
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

    // 댓글 조회 (DTO 변환)
    public CommentDto readComment(Long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // DTO로 변환하여 반환
        return new CommentDto(
                comment.getCommentId(),
                comment.getBoard().getBoardId(),
                comment.getBoard().getTitle(), // Lazy Loading 문제 방지
                comment.getContent(),
                comment.getWriter()
        );
    }

    // 댓글 수정
    @Transactional
    public CommentDto updateComment(Long commentId, CommentDto commentDto) {

        // 1. 댓글 존재 여부 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // 2. 댓글 내용 수정
        comment.setContent(commentDto.getContent());
        comment.setWriter(commentDto.getWriter());

        // 3. DTO로 변환하여 반환
        return new CommentDto(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        // 1. 댓글 존재 여부 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // 2. 댓글 삭제
        commentRepository.delete(comment);
    }
}
