package me.progfrog.unit_test_study.article.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.then;

class ArticleTest {

    @Test
    @DisplayName("Article constructor")
    void constructorArticle() {
        // given
        var board = new Board(5L, "board");

        // when
        var article = Article.builder()
                .id(1L)
                .board(board)
                .subject("subject")
                .content("content")
                .username("user")
                .createdAt(LocalDateTime.now())
                .build();

        // then
        then(article)
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("board.id", 5L)
                .hasFieldOrPropertyWithValue("subject", "subject")
                .hasFieldOrPropertyWithValue("content", "content")
                .hasFieldOrProperty("createdAt");
    }

    @Test
    @DisplayName("Article update")
    void updateArticle() {
        // given
        var article = Article.builder()
                .id(1L)
                .board(new Board(5L, "board"))
                .subject("subject")
                .content("content")
                .username("user")
                .createdAt(LocalDateTime.now())
                .build();

        // when
        article.update("new subject", "new content");

        // then
        then(article)
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("board.id", 5L)
                .hasFieldOrPropertyWithValue("subject", "new subject")
                .hasFieldOrPropertyWithValue("content", "new content");
    }
}