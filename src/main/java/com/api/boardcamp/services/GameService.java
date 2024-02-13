package com.api.boardcamp.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.api.boardcamp.models.dto.GameDTO;
import com.api.boardcamp.exceptions.NotFoundException;
import com.api.boardcamp.exceptions.GameAlreadyExistsException;
import com.api.boardcamp.models.entities.Game;
import com.api.boardcamp.repositories.GameRepository;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Game not found"));
    }

    public Game addGame(GameDTO gameDTO) {
        Game game = new Game();
        game.setName(gameDTO.getName());
        game.setImage(gameDTO.getImage());
        game.setStockTotal(gameDTO.getStockTotal());
        game.setPricePerDay(gameDTO.getPricePerDay());

        if (gameRepository.existsByNameIgnoreCase(gameDTO.getName())) {
            throw new GameAlreadyExistsException("Game alrealdy exists");
        }

        return gameRepository.save(game);
    }
}