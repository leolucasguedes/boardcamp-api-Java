package com.api.boardcamp.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.boardcamp.models.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByNameIgnoreCaseStartingWith(String name);

    boolean existsByNameIgnoreCase(String name);
}
