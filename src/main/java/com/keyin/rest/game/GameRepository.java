package com.keyin.rest.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    public Game findByDateTime(LocalDateTime dateTime);
}
