package com.keyin.rest.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides basic database operations for Game objects.
 */
@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

}
