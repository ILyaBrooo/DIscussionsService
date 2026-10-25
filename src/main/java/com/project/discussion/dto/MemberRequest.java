package com.project.discussion.dto;

public class MemberRequest {
    private String userId; // String
    private String role;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
