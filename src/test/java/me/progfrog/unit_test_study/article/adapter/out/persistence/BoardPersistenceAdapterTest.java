package me.progfrog.unit_test_study.article.adapter.out.persistence;

import me.progfrog.unit_test_study.article.adapter.out.persistence.repository.BoardRepository;
import me.progfrog.unit_test_study.article.domain.Board;
import me.progfrog.unit_test_study.article.domain.out.persistence.BoardJpaEntityFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class BoardPersistenceAdapterTest {
    private BoardPersistenceAdapter adapter;

    private BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        boardRepository = Mockito.mock(BoardRepository.class);

        adapter = new BoardPersistenceAdapter(boardRepository);
    }

    @Test
    void findBoardById() {
        var boardJpaEntity = BoardJpaEntityFixtures.board();
        given(boardRepository.findById(any()))
                .willReturn(Optional.of(boardJpaEntity));

        var result = adapter.findBoardById(1L);

        then(result)
                .isPresent()
                .hasValueSatisfying(board ->
                        then(board)
                                .isInstanceOf(Board.class)
                                .hasFieldOrPropertyWithValue("id", 5L)
                );
    }

}