package com.keyin.rest.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    public void testGetAllGames() {
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        when(gameRepository.findAll()).thenReturn(games);

        List<Game> result = gameService.getAllGames();
        assertEquals(1, result.size());
        verify(gameRepository, times(1)).findAll();
    }

    @Test
    public void testGetGameById() {
        Game game = new Game();
        game.setId(1L);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        Optional<Game> result = gameService.getGameById(1L);
        assertEquals(1L, result.get().getId());
        verify(gameRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateGame() {
        Game game = new Game();
        game.setGameDateTime(LocalDateTime.now());
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        Game result = gameService.createGame(game);
        assertEquals(game.getGameDateTime(), result.getGameDateTime());
        verify(gameRepository, times(1)).save(game);
    }

    @Test
    public void testUpdateGame() {
        Game existingGame = new Game();
        existingGame.setId(1L);
        existingGame.setGameDateTime(LocalDateTime.now());

        Game newGameDetails = new Game();
        newGameDetails.setGameDateTime(LocalDateTime.now().plusHours(1));

        when(gameRepository.findById(1L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(any(Game.class))).thenReturn(newGameDetails);

        Game result = gameService.updateGame(1L, newGameDetails);
        assertEquals(newGameDetails.getGameDateTime(), result.getGameDateTime());
        verify(gameRepository, times(1)).findById(1L);
        verify(gameRepository, times(1)).save(existingGame);
    }

    @Test
    public void testDeleteGame() {
        doNothing().when(gameRepository).deleteById(1L);
        gameService.deleteGame(1L);
        verify(gameRepository, times(1)).deleteById(1L);
    }
}
