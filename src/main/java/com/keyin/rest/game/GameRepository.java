package com.keyin.rest.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    
    // Find games by home team
    List<Game> findByHomeTeamId(Long homeTeamId);
    
    // Find games by away team
    List<Game> findByAwayTeamId(Long awayTeamId);
    
    // Find games by either home or away team
    @Query("SELECT g FROM Game g WHERE g.homeTeam.id = :teamId OR g.awayTeam.id = :teamId")
    List<Game> findByTeamId(@Param("teamId") Long teamId);
    
    // Find games by location
    List<Game> findByLocationContainingIgnoreCase(String location);
    
    // Find games by date range
    List<Game> findByScheduledDateBetween(LocalDateTime startDate, LocalDateTime endDate);
} 