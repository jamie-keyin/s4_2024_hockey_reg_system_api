package com.keyin.rest.game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
    // This gives us basic things like:
    // - save a game
    // - find all games
    // - find a game by its ID
    // - delete a game
}
