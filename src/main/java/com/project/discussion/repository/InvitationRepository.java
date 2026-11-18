package com.project.discussion.repository;

import com.project.discussion.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    // Уже приглашали этого пользователя в это обсуждение?
    boolean existsByDiscussionIdAndUserId(Long discussionId, String userId);

    // Все приглашения пользователя с определённым статусом (PENDING, ACCEPTED...)
    List<Invitation> findByUserIdAndStatus(String userId, Invitation.Status status);

    // Все приглашения одного обсуждения (например, для просмотра создателем)
    List<Invitation> findByDiscussionId(Long discussionId);

    // Удалить приглашение, но только если оно из нужного обсуждения
    @Transactional
    void deleteByDiscussionIdAndId(Long discussionId, Long id);
}
