package com.example.week6.controller;

import com.example.week6.dto.CommentDto;
import com.example.week6.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/create")
    public ResponseEntity<?> postComment(@RequestBody CommentDto commentDto) {
        try {
            commentService.postComment(commentDto);
            return ResponseEntity.ok("댓글이 성공적으로 작성되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error : " + e.getMessage());
        }
    }

    @GetMapping("/read")
    public ResponseEntity<?> readComment(@RequestParam Long commentId) {
        try {
            CommentDto commentDto = commentService.readComment(commentId);
            return ResponseEntity.ok(commentDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "status", "error",
                    "message", "댓글 조회 중 서버 오류가 발생했습니다.",
                    "errorDetail", e.getMessage()
            ));
        }
    }

    // 댓글 수정
    @PutMapping("/update")
    public ResponseEntity<?> updateComment(@RequestParam Long commentId, @RequestBody CommentDto commentDto) {
        try {
            CommentDto updatedComment = commentService.updateComment(commentId, commentDto);
            return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "status", "error",
                    "message", "댓글 수정 중 서버 오류가 발생했습니다.",
                    "errorDetail", e.getMessage()
            ));
        }
    }
}