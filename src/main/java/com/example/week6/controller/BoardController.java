package com.example.week6.controller;

import com.example.week6.dto.BoardDTO;
import com.example.week6.entity.Board;
import com.example.week6.service.BoardService;
import com.example.week6.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final S3Service s3Service;

    // 게시글 하나 띄우기
    @GetMapping("/getBoard")
    public Optional<Board> getBoard(@RequestParam(name = "boardId") Long boardId) {
        return boardService.getBoard(boardId);
    }

    // 게시글 작성하기
    @PostMapping("/postBoard")
    public void postBoard(@RequestBody BoardDTO boardDTO) {

        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .build();

        boardService.postBoard(board);
    }

    // 게시글 수정하기
    @PutMapping("/putBoard")
    public void putBoard(@RequestBody BoardDTO boardDTO) {
        boardService.putBoard(boardDTO);
    }

    // 게시글 삭제하기
    // PathVariable : null 값 X
    @DeleteMapping("/deleteBoard/{boardId}")
    public void deleteBoard(@PathVariable(name="boardId") Long boardId) {
        boardService.deleteBoard(boardId);
    }


    // 이미지 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute BoardDTO boardDTO) {
        try {
            BoardDTO request = BoardDTO.builder()
                    .title(boardDTO.getTitle())
                    .content(boardDTO.getContent())
                    .writer(boardDTO.getWriter())
                    .image(boardDTO.getImage())
                    .build();

            boardService.ImageBoard(request);

            return ResponseEntity.ok("파일 업로드 성공");
        } catch (Exception e) {
            log.error("파일 업로드 실패 : " + e);
            return ResponseEntity.status(400).build();
        }
    }

    //이미지 조회
    @GetMapping("/{boardId}/image")
    public ResponseEntity<?> getImage(@PathVariable(name="boardId") Long boardId) {
        try{
            String imageUrl = boardService.getImageUrl(boardId);
            return ResponseEntity.ok(Map.of("status", "success", "imageUrl", imageUrl));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Map.of("status", "error", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "status", "error", "message", "이미지 조회 중 서버 에러가 발생하였습니다."));
        }
    }

    // 이미지 수정
    @PutMapping("/{boardId}/image")
    public ResponseEntity<?> updateImage(@PathVariable(name="boardId")
                                             Long boardId, @RequestParam("image") MultipartFile imageUrl) {
        try{
            String newImageUrl = boardService.updateImageUrl(boardId, imageUrl);
            return ResponseEntity.ok(Map.of(
                    "status", "success", "imageUrl", newImageUrl));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Map.of("status", "error", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "status", 500, "message", "이미지 수정 중 서버 에러가 발생하였습니다."));
        }
    }

    // 이미지 삭제
    @DeleteMapping("/{boardId}/image")
    public ResponseEntity<?> deleteImage(@PathVariable(name="boardId") Long boardId) {
        try  {
            boardService.deleteImageUrl(boardId);
            return ResponseEntity.ok(Map.of("status", "success", "message", "이미지가 삭제되었습니다."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Map.of("status", "error", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "status", "error", "message", "이미지 삭제 중 서버 에러가 발생하였습니다."));
        }
    }
}