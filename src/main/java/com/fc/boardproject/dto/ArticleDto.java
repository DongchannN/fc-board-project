package com.fc.boardproject.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fc.boardproject.domain.Article} entity
 */
@Getter
@Setter
public class ArticleDto implements Serializable {
    private LocalDateTime createdAt;
    private String createdBy;
    private String title;
    private String content;
    private String hashtag;

    public ArticleDto() {
    }

    public ArticleDto(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }
}