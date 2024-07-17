package me.progfrog.unit_test_study.article.application.port.in;

import me.progfrog.unit_test_study.article.domain.Article;

import java.util.List;

public interface GetArticleUseCase {
    Article getArticleById(Long articleId);

    List<Article> getArticlesByBoard(Long boardId);
}
