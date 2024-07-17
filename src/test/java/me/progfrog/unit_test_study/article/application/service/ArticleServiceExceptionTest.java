package me.progfrog.unit_test_study.article.application.service;

import me.progfrog.unit_test_study.article.adapter.in.api.dto.ArticleDto;
import me.progfrog.unit_test_study.article.application.port.out.CommandArticlePort;
import me.progfrog.unit_test_study.article.application.port.out.LoadArticlePort;
import me.progfrog.unit_test_study.article.application.port.out.LoadBoardPort;
import me.progfrog.unit_test_study.article.domain.Board;
import me.progfrog.unit_test_study.article.domain.BoardFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceExceptionTest {
    private ArticleService sut;

    @Mock
    private LoadArticlePort loadArticlePort;

    @Mock
    private CommandArticlePort commandArticlePort;

    @Mock(strictness = Mock.Strictness.LENIENT) // 1. 실제 사용하지 않는 stub이 있더라도 무시하고 진행하겠다 (<-> 기본값: STRICT_STUBS)
    private LoadBoardPort loadBoardPort;

    private final Board board = BoardFixtures.board();

    @BeforeEach
    void setUp() {
        sut = new ArticleService(loadArticlePort, commandArticlePort, loadBoardPort);
    }

    @Test
    @DisplayName("subject가 정상적이지 않으면 IllegalArgumentException")
    void throwIllegalArgumentException() {
        var request = new ArticleDto.CreateArticleRequest(5L, null, "content", "user");
        given(loadBoardPort.findBoardById(any()))
                .willReturn(Optional.of(board)); // 2. 요 부분은 지우는 것을 권장

        thenThrownBy(() -> sut.createArticle(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("subject should")
                .hasMessage("subject should not empty");
    }
}
