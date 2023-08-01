package com.fc.boardproject.dto.request;

import com.fc.boardproject.dto.ArticleDto;
import com.fc.boardproject.dto.UserAccountDto;

public class ArticleRequest {
    private String title;
    private String content;
    private String hashtag;

    public ArticleRequest() {}

    public ArticleRequest(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static ArticleRequest of (String title, String content, String hashtag) {
        return new ArticleRequest(title, content, hashtag);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtag
        );
    }
}
