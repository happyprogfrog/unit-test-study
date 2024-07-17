package me.progfrog.unit_test_study.article.adapter.out.persistence.repository;

import me.progfrog.unit_test_study.article.adapter.out.persistence.entity.BoardJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardJpaEntity, Long> {
}
