package com.keyin.rest.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface GameRepository extends CrudRepository<Game, Long> {
}
