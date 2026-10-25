package com.project.discussion.repository;

import com.project.discussion.entity.DiscussionMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DiscussionMemberRepository extends JpaRepository<DiscussionMembers, Long> {

    List<DiscussionMembers> findByDiscussionId(Long discussionId);

    boolean existsByDiscussionIdAndUserId(Long discussionId, String userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM DiscussionMembers m WHERE m.discussionId = :discussionId AND m.userId = :userId")
    int deleteByDiscussionIdAndUserId(Long discussionId, String userId);
}
