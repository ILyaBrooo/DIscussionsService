package com.project.discussion.service;

import com.project.discussion.dto.MemberRequest;
import com.project.discussion.entity.DiscussionMembers;
import com.project.discussion.repository.DiscussionMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscussionMemberService {

    private final DiscussionMemberRepository repository;

    public DiscussionMemberService(DiscussionMemberRepository repository) {
        this.repository = repository;
    }

    public boolean isMember(Long discussionId, String userId) {
        if (discussionId == null || userId == null) {
            throw new IllegalArgumentException("Discussion ID and User ID cannot be null");
        }
        return repository.existsByDiscussionIdAndUserId(discussionId, userId);
    }

    public boolean isAdmin(Long discussionId, String userId) {
        if (discussionId == null || userId == null) {
            throw new IllegalArgumentException("Discussion ID and User ID cannot be null");
        }
        return repository.findByDiscussionId(discussionId).stream()
                .anyMatch(m -> m.getUserId().equals(userId) && "ADMIN".equals(m.getRole()));
    }

    public List<DiscussionMembers> getMembers(Long discussionId) {
        if (discussionId == null) {
            throw new IllegalArgumentException("Discussion ID cannot be null");
        }
        return repository.findByDiscussionId(discussionId);
    }

    @Transactional
    public void addMember(Long discussionId, MemberRequest request) {
        if (discussionId == null) {
            throw new IllegalArgumentException("Discussion ID cannot be null");
        }
        if (request == null || request.getUserId() == null) {
            throw new IllegalArgumentException("User ID in request cannot be null");
        }

        if (isMember(discussionId, request.getUserId())) {
            throw new IllegalStateException("User is already a member of this discussion");
        }

        DiscussionMembers member = new DiscussionMembers();
        member.setDiscussionId(discussionId);
        member.setUserId(request.getUserId());

        String role = (request.getRole() != null) ? request.getRole() : "MEMBER";
        member.setRole(role);
        member.setJoinedAt(LocalDateTime.now());

        repository.save(member);
    }

    @Transactional
    public void removeMember(Long discussionId, String userId) {
        if (discussionId == null || userId == null) {
            throw new IllegalArgumentException("Discussion ID and User ID cannot be null");
        }

        List<DiscussionMembers> members = repository.findByDiscussionId(discussionId);

        if (members.stream()
                .anyMatch(m -> m.getUserId().equals(userId) && "ADMIN".equals(m.getRole()))) {
            long adminCount = members.stream()
                    .filter(m -> "ADMIN".equals(m.getRole()))
                    .count();
            if (adminCount == 1) {
                throw new IllegalStateException("Cannot leave: you are the last administrator");
            }
        }

        int deletedCount = repository.deleteByDiscussionIdAndUserId(discussionId, userId);

        if (deletedCount == 0) {
            throw new IllegalStateException("No member found to remove for discussion " + discussionId + " and user " + userId);
        }
    }
}
