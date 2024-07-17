package me.progfrog.unit_test_study.article.adapter.out.persistence;

import me.progfrog.unit_test_study.article.adapter.out.persistence.entity.BoardJpaEntity;
import me.progfrog.unit_test_study.article.adapter.out.persistence.repository.BoardRepository;
import me.progfrog.unit_test_study.article.application.port.out.LoadBoardPort;
import me.progfrog.unit_test_study.article.domain.Board;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BoardPersistenceAdapter implements LoadBoardPort {
    private final BoardRepository boardRepository;

    public BoardPersistenceAdapter(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Optional<Board> findBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .map(BoardJpaEntity::toDomain);
    }
}
