package com.fc.boardproject.service;

import com.fc.boardproject.domain.Article;
import com.fc.boardproject.domain.UserAccount;
import com.fc.boardproject.domain.type.SearchType;
import com.fc.boardproject.dto.ArticleDto;
import com.fc.boardproject.dto.ArticleUpdateDto;
import com.fc.boardproject.dto.ArticleWithCommentsDto;
import com.fc.boardproject.repository.ArticleRepository;
import com.fc.boardproject.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        Page<ArticleDto> resArticleDto = null;

        switch (searchType) {
            case TITLE:
                resArticleDto = articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
                break;
            case CONTENT:
                resArticleDto = articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
                break;
            case ID:
                resArticleDto = articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
                break;
            case NICKNAME:
                resArticleDto = articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
                break;
            case HASHTAG:
                resArticleDto = articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
                break;
        }
        return resArticleDto;
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto searchArticle(long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public void saveArticle(ArticleDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.getUserAccountDto().getUserId());
        articleRepository.save(dto.toEntity(userAccount));
    }

    public void updateArticle(Long articleId, ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(articleId);
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.getUserAccountDto().getUserId());
            if (article.getUserAccount().equals(userAccount)) {
                if (dto.getTitle() != null) {
                    article.setTitle(dto.getTitle());
                }
                if (dto.getContent() != null) {
                    article.setContent(dto.getContent());
                }
                article.setHashtag(dto.getHashtag());
            }
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패, 게시글을 찾을 수 없습니다. - dto: {}", dto);
        }
    }

    public void deleteArticle(long articleId, String userId) {
        articleRepository.deleteByIdAndUserAccount_UserId(articleId, userId);
    }

    public long getArticleCount() {
        return articleRepository.count();
    }
}
