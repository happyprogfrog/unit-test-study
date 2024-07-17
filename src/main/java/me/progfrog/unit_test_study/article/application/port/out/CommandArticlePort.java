package me.progfrog.unit_test_study.article.application.port.out;

import me.progfrog.unit_test_study.article.domain.Article;

public interface CommandArticlePort {
    Article createArticle(Article article);

    Article modifyArticle(Article article);

    void deleteArticle(Long articleId);
}
