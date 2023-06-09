package com.fc.boardproject.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@Disabled("통합환경 테스트 불가능처리")
@AutoConfigureMockMvc
@Transactional // test에서 트랜젝션은 롤백.
@SpringBootTest
public class DataRestTest {

    // TODO : 테스트 데이터 다운받은 후 아래 테스트 수정해보기.

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void givenNothing_whenReqArticles_thenReturnArticlesJsonRes() throws Exception {
        // Given

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/articles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("/application/hal+json")))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void givenNothing_whenReqArticle_thenReturnArticlesJsonRes() throws Exception {
        // Given

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/articles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("/application/hal+json")))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("[api] 게시글 -> 댓글 리스트 조회")
    @Test
    void givenNothing_whenReqArticleCommentFormArticle_thenReturnArticleCommentsJsonRes() throws Exception {
        // Given

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/articles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("/application/hal+json")))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void givenNothing_whenReqArticleComment_thenReturnArticleCommentsJsonRes() throws Exception {
        // Given

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/articles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("/application/hal+json")))
                .andDo(MockMvcResultHandlers.print());
    }
}
