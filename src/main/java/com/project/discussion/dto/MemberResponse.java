package com.project.discussion.dto;

import java.time.LocalDateTime;

public class MemberResponse {
    private Long id;
    private Long discussionId;
    private String userId;
    private String role;
    private LocalDateTime joinedAt;

    // Геттеры и сеттеры для ВСЕХ полей
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDiscussionId() { return discussionId; }
    public void setDiscussionId(Long discussionId) { this.discussionId = discussionId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getJoinedAt() { return joinedAt; }
    public void setJoinedAt(LocalDateTime joinedAt) { this.joinedAt = joinedAt; }
}
