package com.keyin.rest.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class GamesController {
    @Autowired
    private GamesService gamesService;

    //GET route to return all games
    @GetMapping("/games")
    public List <Games> getAllGames() {
        return gamesService.getAllGames();
    }

    //POST route to create a new game
    @PostMapping("/game")
    public Games creatGame (@RequestBody Games game) {
        return gamesService.createGame(game);
    }
}
