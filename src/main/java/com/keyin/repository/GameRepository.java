package com.keyin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyin.domain.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
