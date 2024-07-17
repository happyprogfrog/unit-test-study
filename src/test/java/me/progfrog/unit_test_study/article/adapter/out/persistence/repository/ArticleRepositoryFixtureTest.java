package me.progfrog.unit_test_study.article.adapter.out.persistence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@Sql("/data/ArticleRepositoryFixtureTest.sql")
public class ArticleRepositoryFixtureTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void listAllArticles() {
        var result = articleRepository.findByBoardId(5L);

        then(result)
                .hasSize(2);
    }

    @Test
    @Sql("/data/ArticleRepositoryFixtureTest.listAllArticles2.sql")
    void listAllArticles2() {
        var result = articleRepository.findByBoardId(5L);

        then(result)
                .hasSize(3);
    }
}
