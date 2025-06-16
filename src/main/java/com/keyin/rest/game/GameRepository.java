package com.keyin.rest.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    // This gives us access to basic CRUD functionality:
    // - save a game
    // - find all games
    // - find a game by its ID
    // - delete a game
}
