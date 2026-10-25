package com.project.discussion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateDiscussionRequest {
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @Size(max = 1000)
    private String description;

    private Long creatorId;

    // геттеры, сеттеры, конструктор
}
