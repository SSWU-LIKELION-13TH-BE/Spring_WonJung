package com.example.week6.repository;

import com.example.week6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 특정 사용자 ID로 사용자를 조회하는 메서드
    Optional<User> findByUserId(String userId);

    // 아이디 중복 확인
    boolean existsByUserId(String userId);
}
