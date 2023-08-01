package com.fc.boardproject.service;

import com.fc.boardproject.domain.Article;
import com.fc.boardproject.domain.type.SearchType;
import com.fc.boardproject.dto.ArticleDto;
import com.fc.boardproject.dto.ArticleUpdateDto;
import com.fc.boardproject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글 검색 시 게시글 리스트 반환")
    @Test
    void givenSearchParam_whenSearchingArticles_thenReturnsArticleList() {

        //given

        //when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword", null);

        //then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글 검색 시 게시글 반환")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticleList() {

        //given

        //when
        // ArticleDto article = sut.searchArticle(1L);

        //then
        // assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보 입력 시 게시글 생성")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        //Given

        given(articleRepository.save(ArgumentMatchers.any(Article.class))).willReturn(null);

        //When
        // sut.saveArticle(new ArticleDto(LocalDateTime.now(), "Chan", "title", "content", "hashtag"));

        //Then
        then(articleRepository).should().save(ArgumentMatchers.any(Article.class));
    }

    @DisplayName("게시글 ID, 수정 정보 입력 시 게시글 수정")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() {
        //Given

        given(articleRepository.save(ArgumentMatchers.any(Article.class))).willReturn(null);

        //When
        // sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "hashtag"));

        //Then
        then(articleRepository).should().save(ArgumentMatchers.any(Article.class));
    }

    @DisplayName("게시글 ID 입력 시 게시글 삭제")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        //Given
        Long articleId = 1L;
        String userId = "chan";
        BDDMockito.willDoNothing().given(articleRepository).deleteByIdAndUserAccount_UserId(articleId, userId);

        //When
        sut.deleteArticle(1L, userId);

        //Then
        then(articleRepository).should().deleteByIdAndUserAccount_UserId(articleId, userId);
    }

    @DisplayName("게시글 수를 조회하면, 게시글 수를 반환한다.")
    void givenNothing_whenCountingArticles_thenReturnsArticleCount() {
        // Given
        long expected = 0L;
        given(articleRepository.count()).willReturn(expected);

        // When
        long actual = sut.getArticleCount();

        // Then
        assertThat(actual).isEqualTo(expected);
        then(articleRepository).should().count();
    }
}