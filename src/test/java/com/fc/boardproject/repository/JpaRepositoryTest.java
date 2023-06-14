package com.fc.boardproject.repository;

import com.fc.boardproject.config.JpaConfig;
import com.fc.boardproject.domain.Article;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
@Disabled
@DisplayName("JPS 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
                             @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("Select Test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        //given

        //when
        List<Article> articles = articleRepository.findAll();
        //then
        assertThat(articles)
                .isNotNull()
                .hasSize(0);
    }

    @DisplayName("Insert Test")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        //given
        long count = articleRepository.count();

        //when
        Article article = articleRepository.save(Article.of("new article", "content", "eat"));
        //then
        assertThat(articleRepository.count())
                .isEqualTo(count + 1);
    }

    @DisplayName("Update Test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        //given
        Article savedArticle = articleRepository.save(Article.of("new article", "content", "eat"));
        Article getArticle = articleRepository.findById(savedArticle.getId()).orElseThrow();
        String updatedHashtag = "changedTag";
        getArticle.setHashtag(updatedHashtag);

        //when
//        Article changedArticle = articleRepository.save(getArticle);
//        articleRepository.flush();
        Article changedArticle = articleRepository.saveAndFlush(getArticle);

        //then
        assertThat(changedArticle)
                .hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("Delete Test")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        //given
        Article savedArticle = articleRepository.save(Article.of("new article", "content", "eat"));
        Article getArticle = articleRepository.findById(savedArticle.getId()).orElseThrow();
        long count = articleRepository.count();

        //when
        articleRepository.delete(getArticle);


        //then
        assertThat(articleRepository.count())
                .isEqualTo(count - 1);
    }
}
