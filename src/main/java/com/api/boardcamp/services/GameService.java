package com.api.boardcamp.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.api.boardcamp.models.dto.GameDTO;
import com.api.boardcamp.exceptions.CategoryNotFoundException;
import com.api.boardcamp.exceptions.GameAlreadyExistsException;
import com.api.boardcamp.models.entities.Game;
import com.api.boardcamp.repositories.GameRepository;
import com.api.boardcamp.repositories.CategoryRepository;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;

    public GameService(GameRepository gameRepository, CategoryRepository categoryRepository) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Game> getGames(String name) {
        if (name != null) {
            return gameRepository.findByNameIgnoreCaseStartingWith(name);
        } else {
            return gameRepository.findAll();
        }
    }

    public void addGame(GameDTO gameDTO) {
        Game game = new Game();
        game.setName(gameDTO.getName());
        game.setImage(gameDTO.getImage());
        game.setStockTotal(gameDTO.getStockTotal());
        game.setCategoryId(gameDTO.getCategoryId());
        game.setPricePerDay(gameDTO.getPricePerDay());

        if (!categoryRepository.existsById(gameDTO.getCategoryId())) {
            throw new CategoryNotFoundException("Category not found");
        }

        if (gameRepository.existsByNameIgnoreCase(gameDTO.getName())) {
            throw new GameAlreadyExistsException("Game alrealdy exists");
        }

        gameRepository.save(game);
    }
}