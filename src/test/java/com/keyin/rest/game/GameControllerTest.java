package com.keyin.rest.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.rest.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        gameRepository.deleteAll();
    }

    @Test
    public void testGetGamesEmptyInitially() throws Exception {
        mockMvc.perform(get("/api/games"))
               .andExpect(status().isOk())
               .andExpect(content().json("[]"));
    }

    @Test
    public void testPostGameValid() throws Exception {
        Game game = new Game();
        Team home = new Team();
        home.setId(1); // assumes a team with ID 1 exists
        Team away = new Team();
        away.setId(2); // assumes a team with ID 2 exists

        game.setHomeTeam(home);
        game.setAwayTeam(away);
        game.setLocation("Stadium");
        game.setScheduledDate(LocalDate.now().plusDays(1));

        mockMvc.perform(post("/api/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(game)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("Stadium"));
    }
}
