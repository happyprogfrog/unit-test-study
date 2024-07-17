package me.progfrog.unit_test_study.article.adapter.in.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.progfrog.unit_test_study.article.domain.Article;
import me.progfrog.unit_test_study.article.domain.Board;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleDto {
    public record CreateArticleRequest(
            @NotNull
            Long boardId,
            @NotNull
            String subject,
            @NotNull
            String content,
            @NotEmpty
            String username
    ) { }

    public record UpdateArticleRequest(
            Long id,
            @NotNull
            BoardDto board,
            @NotNull
            String subject,
            @NotNull
            String content,
            @NotEmpty
            String username
    ) { }

    public record ArticleResponse(
            Long id,
            Board board,
            String subject,
            String content,
            String username,
            LocalDateTime createdAt
    ) {
        public static ArticleResponse of(Article article) {
            return new ArticleResponse(
                    article.getId(),
                    article.getBoard(),
                    article.getSubject(),
                    article.getContent(),
                    article.getUsername(),
                    article.getCreatedAt()
            );
        }
    }
}
