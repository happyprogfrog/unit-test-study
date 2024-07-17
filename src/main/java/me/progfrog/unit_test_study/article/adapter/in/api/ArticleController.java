package me.progfrog.unit_test_study.article.adapter.in.api;

import jakarta.validation.Valid;
import me.progfrog.unit_test_study.article.adapter.in.api.dto.ArticleDto;
import me.progfrog.unit_test_study.article.application.port.in.CreateArticleUseCase;
import me.progfrog.unit_test_study.article.application.port.in.DeleteArticleUseCase;
import me.progfrog.unit_test_study.article.application.port.in.GetArticleUseCase;
import me.progfrog.unit_test_study.article.application.port.in.ModifyArticleUseCase;
import me.progfrog.unit_test_study.common.api.dto.CommandResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {
    private final GetArticleUseCase getArticleUseCase;
    private final CreateArticleUseCase createArticleUseCase;
    private final ModifyArticleUseCase modifyArticleUseCase;
    private final DeleteArticleUseCase deleteArticleUseCase;

    public ArticleController(GetArticleUseCase getArticleUseCase,
                             CreateArticleUseCase createArticleUseCase,
                             ModifyArticleUseCase modifyArticleUseCase,
                             DeleteArticleUseCase deleteArticleUseCase) {
        this.getArticleUseCase = getArticleUseCase;
        this.createArticleUseCase = createArticleUseCase;
        this.modifyArticleUseCase = modifyArticleUseCase;
        this.deleteArticleUseCase = deleteArticleUseCase;
    }

    @GetMapping("{articleId}")
    ArticleDto.ArticleResponse getArticle(@PathVariable(name = "articleId") Long articleId) {
        var article = getArticleUseCase.getArticleById(articleId);

        return ArticleDto.ArticleResponse.of(article);
    }

    @GetMapping(params = "boardId")
    List<ArticleDto.ArticleResponse> listArticlesByBoard(@RequestParam(name = "boardId") Long boardId) {
        return getArticleUseCase.getArticlesByBoard(boardId).stream()
                .map(ArticleDto.ArticleResponse::of)
                .toList();
    }

    @PostMapping
    CommandResponse postArticle(@Valid @RequestBody ArticleDto.CreateArticleRequest request) {
        var createdArticle = createArticleUseCase.createArticle(request);
        return new CommandResponse(createdArticle.getId());
    }

    @PutMapping
    CommandResponse putArticle(@Valid @RequestBody ArticleDto.UpdateArticleRequest request) {
        var article = modifyArticleUseCase.modifyArticle(request);
        return new CommandResponse(article.getId());
    }

    @DeleteMapping("{articleId}")
    void deleteArticle(@PathVariable(name = "articleId") Long articleId) {
        deleteArticleUseCase.deleteArticle(articleId);
    }
}
