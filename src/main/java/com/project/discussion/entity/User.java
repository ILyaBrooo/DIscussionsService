package com.project.discussion.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiscussionMembers> members = new ArrayList<>();

}