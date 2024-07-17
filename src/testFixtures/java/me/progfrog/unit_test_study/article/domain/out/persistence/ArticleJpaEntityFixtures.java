package me.progfrog.unit_test_study.article.domain.out.persistence;

import me.progfrog.unit_test_study.article.adapter.out.persistence.entity.ArticleJpaEntity;
import me.progfrog.unit_test_study.article.adapter.out.persistence.entity.BoardJpaEntity;

import java.time.LocalDateTime;

public class ArticleJpaEntityFixtures {
    public static ArticleJpaEntity entity(Long index) {
        var boardJpaEntity = new BoardJpaEntity("board");

        return new ArticleJpaEntity(boardJpaEntity, "subject" + index, "content" + index,
                "username" + index, LocalDateTime.parse("2023-02-10T11:12:33").plusDays(index));
    }

    public static ArticleJpaEntity entity() {
        var boardJpaEntity = new BoardJpaEntity("board");

        return new ArticleJpaEntity(boardJpaEntity, "subject", "content", "username",
                LocalDateTime.parse("2023-02-10T11:12:33"));
    }
}
