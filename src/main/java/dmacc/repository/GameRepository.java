package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dmacc.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
