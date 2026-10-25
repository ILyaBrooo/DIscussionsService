package com.project.discussion.dto;

import jakarta.validation.constraints.Size;

public class UpdateDiscussionRequest {
    private Long id;

    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    // Конструктор по умолчанию (нужен для десериализации JSON)
    public UpdateDiscussionRequest() {
    }

    // Конструктор со всеми полями (удобен для тестов и явного создания объектов)
    public UpdateDiscussionRequest(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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
}
