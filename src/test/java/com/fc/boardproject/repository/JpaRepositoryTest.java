package com.fc.boardproject.repository;

import com.fc.boardproject.config.JpaConfig;
import com.fc.boardproject.domain.Article;
import com.fc.boardproject.domain.UserAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
@Disabled
@DisplayName("JPA 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
                             @Autowired ArticleCommentRepository articleCommentRepository,
                             @Autowired UserAccountRepository userAccountRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.userAccountRepository = userAccountRepository;
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
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("newChan", "pw", null, null, null));
        Article article = Article.of(userAccount, "new Article", "content", "eat");

        //when
        articleRepository.save(article);

        //then
        assertThat(articleRepository.count())
                .isEqualTo(count + 1);
    }

    @DisplayName("Update Test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        //given
        Article getArticle = articleRepository.findById(1L).orElseThrow();
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
        Article getArticle = articleRepository.findById(1L).orElseThrow();
        long count = articleRepository.count();

        //when
        articleRepository.delete(getArticle);


        //then
        assertThat(articleRepository.count())
                .isEqualTo(count - 1);
    }

    @EnableJpaAuditing
    @TestConfiguration
    public static class TestJpaConfig {
        @Bean
        public AuditorAware<String> auditorAware() {
            return () -> Optional.of("chan");
        }
    }

}
