package me.progfrog.unit_test_study.article.application.service;

import me.progfrog.unit_test_study.article.application.port.out.CommandArticlePort;
import me.progfrog.unit_test_study.article.application.port.out.LoadArticlePort;
import me.progfrog.unit_test_study.article.application.port.out.LoadBoardPort;
import me.progfrog.unit_test_study.article.domain.ArticleFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ArticleServiceJUnitMockitoTest {
    private ArticleService sut;

    private LoadArticlePort loadArticlePort;
    private CommandArticlePort commandArticlePort;
    private LoadBoardPort loadBoardPort;

    @BeforeEach
    void setUp() {
        loadArticlePort = Mockito.mock(LoadArticlePort.class);
        commandArticlePort = Mockito.mock(CommandArticlePort.class);
        loadBoardPort = Mockito.mock(LoadBoardPort.class);

        sut = new ArticleService(loadArticlePort, commandArticlePort, loadBoardPort);
    }

    @Test
    @DisplayName("articleId로 조회 시 Article 반환")
    void return_Article() {
        var article = ArticleFixtures.article();
        Mockito.when(loadArticlePort.findArticleById(any()))
                .thenReturn(Optional.of(article));

        var result = sut.getArticleById(1L);

        then(result)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", article.getId())
                .hasFieldOrPropertyWithValue("board.id", article.getBoard().getId())
                .hasFieldOrPropertyWithValue("subject", article.getSubject())
                .hasFieldOrPropertyWithValue("content", article.getContent())
                .hasFieldOrPropertyWithValue("username", article.getUsername())
                .hasFieldOrPropertyWithValue("createdAt", article.getCreatedAt());

        // verify(loadArticlePort).findArticlesByBoardId(1L);
    }

    @Test
    @DisplayName("BDDStyle Board의 Article 목록 조회")
    void getArticleByBoard_listArticles() {
        // given
        var article1 = ArticleFixtures.article(1L);
        var article2 = ArticleFixtures.article(2L);
        BDDMockito.given(loadArticlePort.findArticlesByBoardId(any()))
                .willReturn(List.of(article1, article2));

        // when
        var result = sut.getArticlesByBoard(5L);

        // then
        then(result)
                .hasSize(2)
                .extracting("board.id").containsOnly(5L);
    }

    @Test
    @DisplayName("Article 삭제")
    void deleteArticle() {
        // given
        BDDMockito.willDoNothing()
                .given(commandArticlePort).deleteArticle(any());

        // when
        sut.deleteArticle(1L);

        // then
        verify(commandArticlePort).deleteArticle(1L); // 반환 받는 값이 없기 때문에, 내부 동작이 이루어 졌는지 확인할 수 밖에 없음
    }
}
