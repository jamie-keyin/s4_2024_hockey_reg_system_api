package com.keyin.rest.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllGames() throws Exception {
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        when(gameService.getAllGames()).thenReturn(games);

        mockMvc.perform(get("/game"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(games.size()));
    }

    @Test
    public void testGetGameById() throws Exception {
        Game game = new Game();
        game.setId(1L);
        when(gameService.getGameById(1L)).thenReturn(Optional.of(game));

        mockMvc.perform(get("/game/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testCreateGame() throws Exception {
        Game game = new Game();
        game.setId(1L);
        game.setGameDateTime(LocalDateTime.now());

        when(gameService.createGame(any(Game.class))).thenReturn(game);

        mockMvc.perform(post("/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(game)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testUpdateGame() throws Exception {
        Game game = new Game();
        game.setId(1L);
        game.setGameDateTime(LocalDateTime.now());

        when(gameService.updateGame(any(Long.class), any(Game.class))).thenReturn(game);

        mockMvc.perform(put("/game/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(game)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testDeleteGame() throws Exception {
        doNothing().when(gameService).deleteGame(1L);

        mockMvc.perform(delete("/game/1"))
                .andExpect(status().isNoContent());
    }
}
