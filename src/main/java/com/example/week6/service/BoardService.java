package com.example.week6.service;

import com.example.week6.dto.BoardDTO;
import com.example.week6.entity.Board;
import com.example.week6.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

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

}
