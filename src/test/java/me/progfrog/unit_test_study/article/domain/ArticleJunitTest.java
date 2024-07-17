package me.progfrog.unit_test_study.article.domain;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

public class ArticleJunitTest {

    @BeforeAll
    static void initAll() {
        System.out.println("Before All\n");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("After All");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before Each");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After Each\n");
    }

    @Test
    @DisplayName("성공 테스트 - Article 생성")
    void constructArticle() {
        // Arrange (준비)
        var board = new Board(5L, "board");

        // Act (실행)
        var article = Article.builder()
                .id(1L)
                .board(board)
                .subject("subject")
                .content("content")
                .username("user")
                .createdAt(LocalDateTime.now())
                .build();

        // Assert (검증)
        Assertions.assertEquals(1L, article.getId());
        Assertions.assertTrue(article.getBoard().equals(board));
        Assertions.assertEquals("subject", article.getSubject());
        Assertions.assertEquals("content", article.getContent());
        Assertions.assertNotEquals("subject2", article.getSubject());
        Assertions.assertNotNull(article.getCreatedAt());

        System.out.println("succeedingTest");
    }

    @Test
    @DisplayName("실패 테스트")
    void failingTest() {
        // expected 혹은 actual 을 다른 값으로 바꿔서 test fail 시키기
        Assertions.assertEquals(4, 2 + 2, "테스트 실패시 출력되는 failure message");

        System.out.println("failingTest");
    }

    @Test
    @Disabled("이 테스트를 Disable 이유")
    void skippingTest() {
        System.out.println("DisabledTest");

        Assertions.assertEquals(3, 1 + 2);
    }
}
