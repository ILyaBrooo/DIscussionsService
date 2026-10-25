package com.project.discussion.dto;

public class UpdateMemberRequest {
    private Long discussionId;
    private Long userId;
    private String role; // например, "ADMIN", "MEMBER"

    // Конструктор по умолчанию (нужен для десериализации JSON)
    public UpdateMemberRequest() {
    }

    // Конструктор со всеми полями
    public UpdateMemberRequest(Long discussionId, Long userId, String role) {
        this.discussionId = discussionId;
        this.userId = userId;
        this.role = role;
    }

    // Геттеры
    public Long getDiscussionId() {
        return discussionId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    // Сеттеры
    public void setDiscussionId(Long discussionId) {
        this.discussionId = discussionId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
