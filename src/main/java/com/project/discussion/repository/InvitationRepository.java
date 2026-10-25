package com.project.discussion.repository;

import com.project.discussion.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    boolean existsByInviterIdAndInviteeId(BigInteger inviterId, BigInteger inviteeId);

    List<Invitation> findByInviteeIdAndStatus(BigInteger inviteeId, String status);

    void deleteByDiscussionIdAndId(BigInteger discussionId, Long id);
}
