package com.example.week6.repository;

import com.example.week6.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 게시글 가져오기 read (get)
    // Optional은 값이 있을 수도 있고, 없을 수도 있는 상황을 처리하는 컨테이너 객체 -> NullPointException 방지
    Optional<Board> findByBoardId(Long boardId);

    // 게시글 삭제하기 delete (delete)
    void deleteByBoardId(Long boardId);
}