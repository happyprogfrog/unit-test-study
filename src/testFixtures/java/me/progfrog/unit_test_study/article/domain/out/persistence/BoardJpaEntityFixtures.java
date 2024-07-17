package me.progfrog.unit_test_study.article.domain.out.persistence;

import me.progfrog.unit_test_study.article.adapter.out.persistence.entity.BoardJpaEntity;
import org.springframework.test.util.ReflectionTestUtils;

public class BoardJpaEntityFixtures {
    public static BoardJpaEntity board() {
        var boardJpaEntity = new BoardJpaEntity("board");
        ReflectionTestUtils.setField(boardJpaEntity, "id", 5L);

        return boardJpaEntity;
    }
}
