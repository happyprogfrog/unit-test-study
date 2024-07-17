package me.progfrog.unit_test_study.article.application.service;

import me.progfrog.unit_test_study.article.adapter.in.api.dto.ArticleDto;
import me.progfrog.unit_test_study.article.application.port.in.CreateArticleUseCase;
import me.progfrog.unit_test_study.article.application.port.in.DeleteArticleUseCase;
import me.progfrog.unit_test_study.article.application.port.in.GetArticleUseCase;
import me.progfrog.unit_test_study.article.application.port.in.ModifyArticleUseCase;
import me.progfrog.unit_test_study.article.application.port.out.CommandArticlePort;
import me.progfrog.unit_test_study.article.application.port.out.LoadArticlePort;
import me.progfrog.unit_test_study.article.application.port.out.LoadBoardPort;
import me.progfrog.unit_test_study.article.domain.Article;
import me.progfrog.unit_test_study.common.exception.AccessDeniedException;
import me.progfrog.unit_test_study.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ArticleService implements GetArticleUseCase, CreateArticleUseCase, ModifyArticleUseCase, DeleteArticleUseCase {
    private final LoadArticlePort loadArticlePort;
    private final CommandArticlePort commandArticlePort;
    private final LoadBoardPort loadBoardPort;

    public ArticleService(LoadArticlePort loadArticlePort, CommandArticlePort commandArticlePort, LoadBoardPort loadBoardPort) {
        this.loadArticlePort = loadArticlePort;
        this.commandArticlePort = commandArticlePort;
        this.loadBoardPort = loadBoardPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Article getArticleById(Long articleId) {
        return loadArticlePort.findArticleById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("id: " + articleId + " 게시물이 없습니다."));
    }

    @Override
    public List<Article> getArticlesByBoard(Long boardId) {
        return loadArticlePort.findArticlesByBoardId(boardId);
    }

    @Override
    public Article createArticle(ArticleDto.CreateArticleRequest request) {
        Assert.hasLength(request.subject(), "subject should not empty");
        Assert.hasLength(request.content(), "content should not empty");
        Assert.hasLength(request.username(), "username should not empty");

        var board = loadBoardPort.findBoardById(request.boardId())
                .orElseThrow();
        var article = Article.builder()
                .board(board)
                .subject(request.subject())
                .content(request.content())
                .username(request.username())
                .createdAt(LocalDateTime.now())
                .build();

        return commandArticlePort.createArticle(article);
    }

    @Override
    public Article modifyArticle(ArticleDto.UpdateArticleRequest request) {
        Article article = loadArticlePort.findArticleById(request.id())
                .orElseThrow();

        if (!article.getUsername().equals(request.username())) {
            throw new AccessDeniedException("");
        }

        article.update(request.subject(), request.content());

        return commandArticlePort.modifyArticle(article);
    }

    @Override
    public void deleteArticle(Long articleId) {
        commandArticlePort.deleteArticle(articleId);
    }
}
