package com.example.week6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name="member_id")
    private Long memberId;

    @Column(length = 15, nullable = false)
    private String name;

    @Column(length=100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length=20, nullable = false)
    private String contact;
}
