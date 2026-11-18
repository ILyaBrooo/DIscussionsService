package com.project.discussion.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "invitations")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Внешний ключ на обсуждение. Объектную ссылку на Discussions убрали,
    // потому что она дублировала эту же колонку (discussion_id).
    @Column(name = "discussion_id")
    private Long discussionId;

    private String userId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    public enum Status {
        PENDING, ACCEPTED, DECLINED, EXPIRED
    }

    // Конструктор по умолчанию (обязателен для JPA)
    public Invitation() {
    }

    // Конструктор со всеми полями
    public Invitation(Long id, Long discussionId, String userId, Status status,
                      LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.id = id;
        this.discussionId = discussionId;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    // Геттеры
    public Long getId() {
        return id;
    }

    public Long getDiscussionId() {
        return discussionId;
    }

    public String getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    // Сеттеры
    public void setId(Long id) {
        this.id = id;
    }

    public void setDiscussionId(Long discussionId) {
        this.discussionId = discussionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
