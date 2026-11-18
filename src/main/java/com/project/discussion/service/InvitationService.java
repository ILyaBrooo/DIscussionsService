package com.project.discussion.service;

import com.project.discussion.dto.InvitationCreateRequest;
import com.project.discussion.entity.Invitation;
import com.project.discussion.repository.InvitationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InvitationService {

    private final InvitationRepository repository;

    public InvitationService(InvitationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Invitation createInvitation(Long discussionId, InvitationCreateRequest request) {
        if (request.getInviteeId() == null) {
            throw new IllegalArgumentException("Invitee ID cannot be null");
        }

        String inviteeId = String.valueOf(request.getInviteeId());

        // Нельзя приглашать одного и того же пользователя в одно обсуждение дважды
        if (repository.existsByDiscussionIdAndUserId(discussionId, inviteeId)) {
            throw new IllegalStateException("User already invited to this discussion");
        }

        Invitation invitation = new Invitation();
        invitation.setDiscussionId(discussionId);
        invitation.setUserId(inviteeId);
        invitation.setStatus(Invitation.Status.PENDING);
        invitation.setCreatedAt(LocalDateTime.now());
        invitation.setExpiresAt(LocalDateTime.now().plusDays(7));

        return repository.save(invitation);
    }
}
