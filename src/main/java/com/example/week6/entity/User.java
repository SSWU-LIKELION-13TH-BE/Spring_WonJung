package com.example.week6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // Id (PK)

    @Column(name="user_id", length = 20, nullable = false, unique = true)
    private String userId;

    @Column(name="password", length = 255, nullable = false)
    private String password;

    @Column(name="name", length = 20, nullable = false)
    private String name;

    @Column(name="profile_image", length = 1000)
    private String profileImage;


}
