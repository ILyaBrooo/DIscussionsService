package com.project.discussion.dto;

public class InvitationCreateRequest {
    private Long discussionId;
    private Long inviteeId;      // ID пользователя, которого приглашают
    private Long inviterId;      // ID пользователя, который приглашает

    public Long getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Long discussionId) {
        this.discussionId = discussionId;
    }

    public Long getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(Long inviteeId) {
        this.inviteeId = inviteeId;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }
}

