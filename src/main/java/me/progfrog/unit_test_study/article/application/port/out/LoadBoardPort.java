package me.progfrog.unit_test_study.article.application.port.out;

import me.progfrog.unit_test_study.article.domain.Board;

import java.util.Optional;

public interface LoadBoardPort {
    Optional<Board> findBoardById(Long boardId);
}
