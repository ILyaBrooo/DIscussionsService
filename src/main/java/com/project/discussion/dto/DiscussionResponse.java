package com.project.discussion.dto;

import java.time.LocalDateTime;

public class DiscussionResponse {
    private Long id;
    private String title;
    private String description;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Конструктор по умолчанию (обязателен для JSON-сериализаторов, например, Jackson)
    public DiscussionResponse() {
    }

    // Конструктор со всеми полями (удобно использовать в сервисе при формировании ответа)
    public DiscussionResponse(Long id, String title, String description, Long creatorId,
                              LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Геттеры
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Сеттеры
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
