package com.keyin.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // No extra methods needed for basic CRUD (findAll, save, etc.)
}
