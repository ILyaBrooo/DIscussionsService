package com.project.discussion.entity;

import java.io.Serializable;
import java.util.Objects;

public class DiscussionMemberId implements Serializable {
    private Long discussionId;
    private String userId;

    public DiscussionMemberId() {}

    public DiscussionMemberId(Long discussionId, String userId) {
        this.discussionId = discussionId;
        this.userId = userId;
    }

    // Геттеры и сеттеры
    public Long getDiscussionId() { return discussionId; }
    public void setDiscussionId(Long discussionId) { this.discussionId = discussionId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscussionMemberId that = (DiscussionMemberId) o;
        return Objects.equals(discussionId, that.discussionId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discussionId, userId);
    }
}
