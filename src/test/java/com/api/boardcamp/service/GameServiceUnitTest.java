package com.api.boardcamp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.api.boardcamp.BoardcampApplication;
import com.api.boardcamp.models.dto.GameDTO;
import com.api.boardcamp.exceptions.NegativeValueException;
import com.api.boardcamp.models.entities.Game;
import com.api.boardcamp.repositories.GameRepository;
import com.api.boardcamp.services.GameService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = BoardcampApplication.class)
@ExtendWith(MockitoExtension.class)
class GameUnitTest {
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGames() {

        Game game1 = new Game(1L, "Game 1", "image1.jpg", 5, 1500.0);
        Game game2 = new Game(2L, "Game 2", "image2.jpg", 3, 2000.0);
        List<Game> gamesList = new ArrayList<>();
        gamesList.add(game1);
        gamesList.add(game2);

        when(gameRepository.findAll()).thenReturn(gamesList);

        List<Game> result = gameService.getGames();

        assertEquals(2, result.size());
        assertEquals("Game 1", result.get(0).getName());
        assertEquals("Game 2", result.get(1).getName());

    }

    @Test
    void testGetGameById() {

        Long gameId = 1L;
        Game game = new Game();
        game.setId(gameId);
        game.setName("Game 1");
        game.setImage("image1.jpg");
        game.setStockTotal(5);
        game.setPricePerDay(1500.0);

        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        Game retrievedGame = gameService.getGameById(gameId);

        assertEquals(game, retrievedGame);
    }

    @Test
    void givenNegativeStockTotal_whenCreatingGame_thenThrowBadRequestException() {
        GameDTO dto = new GameDTO("nome", "imagem", -1, 1000.0);

        assertThrows(NegativeValueException.class, () -> gameService.addGame(dto));
    }
}