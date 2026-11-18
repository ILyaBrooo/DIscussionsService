package com.project.discussion.service;

import com.project.discussion.dto.CreateDiscussionRequest;
import com.project.discussion.dto.MemberRequest;
import com.project.discussion.dto.UpdateDiscussionRequest;
import com.project.discussion.entity.Discussions;
import com.project.discussion.repository.DiscussionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
public class DiscussionService {

    private final DiscussionRepository repository;
    private final DiscussionMemberService memberService;

    public DiscussionService(DiscussionRepository repository,
                             DiscussionMemberService memberService) {
        this.repository = repository;
        this.memberService = memberService;
    }

    @Transactional
    public Discussions createDiscussion(CreateDiscussionRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (request.getCreatorId() == null) {
            throw new IllegalArgumentException("Creator ID cannot be null");
        }

        Discussions discussion = new Discussions();
        discussion.setCreatorId(BigInteger.valueOf(request.getCreatorId()));
        discussion.setTitle(request.getTitle());
        discussion.setDescription(request.getDescription());
        discussion.setCreatedAt(LocalDateTime.now());
        discussion.setUpdatedAt(LocalDateTime.now());

        Discussions created = repository.save(discussion);

        // Создатель автоматически становится администратором
        MemberRequest creatorMember = new MemberRequest();
        creatorMember.setUserId(String.valueOf(request.getCreatorId()));
        creatorMember.setRole("ADMIN");
        memberService.addMember(created.getId(), creatorMember);

        return created;
    }

    public Discussions getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discussion not found: " + id));
    }

    @Transactional
    public Discussions updateDiscussion(Long id, UpdateDiscussionRequest request) {
        Discussions discussion = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discussion not found: " + id));

        if (request.getTitle() != null) {
            discussion.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            discussion.setDescription(request.getDescription());
        }
        discussion.setUpdatedAt(LocalDateTime.now());

        return repository.save(discussion);
    }

    @Transactional
    public void deleteDiscussion(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Discussion not found: " + id);
        }
        repository.deleteById(id);
    }
}
