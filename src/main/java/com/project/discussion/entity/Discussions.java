package com.project.discussion.entity;

import jakarta.persistence.*;
import java.math.BigInteger;
import java.time.*;
import java.util.*;

@Entity
@Table (schema = "Discussions", name = "Discussions")
public class Discussions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigInteger creatorId;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiscussionMembers> members = new ArrayList<>();

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invitation> invitations = new ArrayList<>();
}
