package com.fc.boardproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fc.boardproject.domain.Article} entity
 */
@Getter
@Setter
public class ArticleUpdateDto implements Serializable {
    private String title;
    private String content;
    private String hashtag;

    public ArticleUpdateDto() {
    }

    public ArticleUpdateDto(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static ArticleUpdateDto of(String title, String content, String hashtag) {
        return new ArticleUpdateDto(title, content, hashtag);
    }
}