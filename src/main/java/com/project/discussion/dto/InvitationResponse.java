package com.project.discussion.dto;

import com.project.discussion.entity.Invitation;
import java.time.LocalDateTime;

public class InvitationResponse {
    private Long id;
    private Long discussionId;
    private String userId;
    private Invitation.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    public InvitationResponse() {
    }

    public InvitationResponse(Long id, Long discussionId, String userId, Invitation.Status status,
                              LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.id = id;
        this.discussionId = discussionId;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    // Геттеры
    public Long getId() { return id; }
    public Long getDiscussionId() { return discussionId; }
    public String getUserId() { return userId; }
    public Invitation.Status getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setDiscussionId(Long discussionId) { this.discussionId = discussionId; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setStatus(Invitation.Status status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
}
