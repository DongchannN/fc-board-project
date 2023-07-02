package com.fc.boardproject.service;

import com.fc.boardproject.domain.Article;
import com.fc.boardproject.dto.ArticleCommentDto;
import com.fc.boardproject.repository.ArticleCommentRepository;
import com.fc.boardproject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {
    @InjectMocks
    private ArticleCommentService sut;
    @Mock
    private ArticleCommentRepository articleCommentRepository;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 ID로 조회 시 해당 댓글 리스트 반환")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnComments() {
        //Given
        Long articleId = 1L;
        BDDMockito.given(articleRepository.findById(articleId))
                .willReturn(Optional.of(Article.of("title", "content", "#java")));
        //When
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId);
        //Then
        assertThat(articleComments).isNotNull();
        BDDMockito.then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력 시 댓글 저장")
    @Test
    void givenCommentInfo_whenSavingComments_thenReturnSaveComment() {
        //Given
        Long articleId = 1L;
        BDDMockito.given(articleRepository.findById(articleId))
                .willReturn(Optional.of(Article.of("title", "content", "#java")));
        //When
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId);
        //Then
        assertThat(articleComments).isNotNull();
        BDDMockito.then(articleRepository).should().findById(articleId);
    }
}