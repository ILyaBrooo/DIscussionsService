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

        if (repository.existsByDiscussionIdAndUserId(discussionId, request.getUserId())) {
            throw new IllegalStateException("User is already a member of this discussion");
        }

        DiscussionMembers member = new DiscussionMembers();
        member.setDiscussionId(discussionId);
        member.setUserId(request.getUserId()); // String

        String role = (request.getRole() != null) ? request.getRole() : "MEMBER";
        member.setRole(role);
        member.setJoinedAt(LocalDateTime.now());

        repository.save(member);
    }

    @Transactional
    public void removeMember(Long discussionId, String userId) { // String
        if (discussionId == null || userId == null) {
            throw new IllegalArgumentException("Discussion ID and User ID cannot be null");
        }

        int deletedCount = repository.deleteByDiscussionIdAndUserId(discussionId, userId);

        if (deletedCount == 0) {
            throw new IllegalStateException("No member found to remove for discussion " + discussionId + " and user " + userId);
        }
    }
}
