package com.project.discussion.service;

import com.project.discussion.dto.CreateDiscussionRequest;
import com.project.discussion.dto.UpdateDiscussionRequest;
import com.project.discussion.repository.DiscussionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscussionService {

    private final DiscussionRepository repository;

    public DiscussionService(DiscussionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createDiscussion(CreateDiscussionRequest request) {
        // TODO: маппинг request -> entity + repository.save()
    }

    public Object getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discussion not found: " + id));
    }

    @Transactional
    public void updateDiscussion(Long id, UpdateDiscussionRequest request) {
        // TODO: найти сущность, обновить поля из request, вызвать repository.save()
    }

    @Transactional
    public void deleteDiscussion(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Discussion not found: " + id);
        }
        repository.deleteById(id);
    }
}
