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

        Invitation invitation = new Invitation();
        invitation.setDiscussionId(discussionId); // Или можно брать из request.getDiscussionId(), если он там есть
        invitation.setUserId(String.valueOf(request.getInviteeId())); // Приводим к String, если поле в Entity типа String
        invitation.setStatus(Invitation.Status.PENDING);
        invitation.setCreatedAt(LocalDateTime.now());
        invitation.setExpiresAt(LocalDateTime.now().plusDays(7));

        return repository.save(invitation);
    }
}
