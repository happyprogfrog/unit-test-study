package me.progfrog.unit_test_study.article.application.port.out;

import me.progfrog.unit_test_study.article.domain.Article;

import java.util.List;
import java.util.Optional;

public interface LoadArticlePort {
    Optional<Article> findArticleById(Long articleId);

    List<Article> findArticlesByBoardId(Long boardId);
}
