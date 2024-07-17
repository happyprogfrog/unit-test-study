package me.progfrog.unit_test_study.article.application.port.in;

import me.progfrog.unit_test_study.article.adapter.in.api.dto.ArticleDto;
import me.progfrog.unit_test_study.article.domain.Article;

public interface ModifyArticleUseCase {
    Article modifyArticle(ArticleDto.UpdateArticleRequest request);
}
