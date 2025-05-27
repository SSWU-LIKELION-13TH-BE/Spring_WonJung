package com.example.week6.service;

import com.example.week6.dto.BoardDTO;
import com.example.week6.entity.Board;
import com.example.week6.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final S3Service s3Service;

    // 들어온 boardId 값과 db의 boardId 값이 일치하는 row 가져오기
    public Optional<Board> getBoard(Long boardId){
        return boardRepository.findByBoardId(boardId);
    }

    // 게시글 하나 작성
    public void postBoard(Board board){
        boardRepository.save(board);
    }

    // 게시글 수정
    @Transactional
    public void putBoard(BoardDTO boardDTO){
        Board board = Board.builder()
                .boardId(boardDTO.getBoardId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .postDate(LocalDate.now())
                .build();

        // save 시 기존에 있는 객체라면 merge
        // id를 전달하지 않을 경우에는 insert 수행
        // id를 전달할 경우
            // 데이터가 있다면 -> UPDATE
            // 데이터가 없다면 -> INSERT

        boardRepository.save(board);
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long boardId){
        boardRepository.deleteById(boardId);
    }

    // 이미지 포함 게시글 생성
    @Transactional
    public void ImageBoard(BoardDTO request) throws IOException {

        String savedImageURI = s3Service.upload(request.getImage());    // 이미지 s3에 업로드하고 url 가져오

        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getWriter())
                .image(savedImageURI)       // img url 넣기
                .build();

        boardRepository.save(board);

    }

    // boardId를 통해 이미지 조회
    @Transactional
    public String getImageUrl(Long boardId) throws IOException {
        Board board = boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다."));

        return board.getImage();
    }

    // 이미지 수정
    @Transactional
    public String updateImageUrl(Long boardId, MultipartFile newImage) throws IOException {
        Board board = boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다."));

        // 기존 이미지 삭제
        String oldImage = board.getImage();
        if (oldImage != null && !oldImage.isEmpty()) {
            s3Service.deleteImageUrl(oldImage);
        }

        // 새 이미지 업로드 및 객체에 저장
        String newImageUrl = s3Service.upload(newImage);
        board.setImage(newImageUrl);

        return newImageUrl;
    }

    // 이미지 삭제
    @Transactional
    public void deleteImageUrl(Long boardId) throws IOException {
        Board board = boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다."));


        String imageUrl = board.getImage();

        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new IllegalArgumentException("삭제할 이미지가 존재하지 않습니다.");
        }

        // s3에서 이미지 삭제
        s3Service.deleteImageUrl(board.getImage());

        // DB 이미지 삭제
        board.setImage(null);
        boardRepository.save(board);
    }
}