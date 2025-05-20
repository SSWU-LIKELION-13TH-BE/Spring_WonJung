package com.example.week6.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(length = 15, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(length = 15, nullable = false)
    private String writer;

    private LocalDate postDate;

    // image Url 저장
    @Column
    private String image;

    @PrePersist // jpa의 롤백 메서드 엔티티가 처음 저장되기 직전에 실행
    // 새로운 row 생성 시 현재 날짜 지정
    protected void onCreate() {
        this.postDate = LocalDate.now();
    }
}
