package com.fc.boardproject.service;

import com.fc.boardproject.domain.Article;
import com.fc.boardproject.domain.ArticleComment;
import com.fc.boardproject.domain.UserAccount;
import com.fc.boardproject.dto.ArticleCommentDto;
import com.fc.boardproject.dto.UserAccountDto;
import com.fc.boardproject.repository.ArticleCommentRepository;
import com.fc.boardproject.repository.ArticleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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
        ArticleComment expected = createArticleComment("content");

        BDDMockito.given(articleCommentRepository.findByArticle_Id(articleId))
                .willReturn(List.of(expected));

        //When
        List<ArticleCommentDto> actual = sut.searchArticleComments(articleId);
        //Then
        assertThat(actual).hasSize(1)
                .first().hasFieldOrPropertyWithValue("content", expected.getContent());

        BDDMockito.then(articleCommentRepository).should().findByArticle_Id(articleId);
    }

    @Disabled("구현중")
    @DisplayName("댓글 정보를 입력 시 댓글 저장")
    @Test
    void givenCommentInfo_whenSavingComments_thenReturnSaveComment() {
        //Given
        ArticleCommentDto dto = createArticleCommentDto("댓글");

        BDDMockito.given(articleRepository.getReferenceById(dto.getArticleId()))
                .willReturn(null);
        //When
        // sut.saveArticleComment(dto);

        //Then
        BDDMockito.then(articleRepository).should().getReferenceById(dto.getArticleId());
        BDDMockito.then(articleCommentRepository).should().save(ArgumentMatchers.any(ArticleComment.class));
    }

    private ArticleCommentDto createArticleCommentDto(String content) {
        return ArticleCommentDto.of(
                1L,
                1L,
                createUserAccountDto(),
                content,
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                1L,
                "uno",
                "password",
                "uno@mail.com",
                "Uno",
                "This is memo",
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

    private ArticleComment createArticleComment(String content) {
        return ArticleComment.of(
                Article.of(createUserAccount(), "title", "content", "hashtag"),
                createUserAccount(),
                content
        );
    }

    private UserAccount createUserAccount() {
        return UserAccount.of(
                "uno",
                "password",
                "uno@email.com",
                "Uno",
                null
        );
    }

    private Article createArticle() {
        return Article.of(
                createUserAccount(),
                "title",
                "content",
                "#java"
        );
    }
}