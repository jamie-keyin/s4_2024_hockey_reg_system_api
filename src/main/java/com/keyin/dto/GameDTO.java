package com.keyin.dto;

import java.time.LocalDate;

public class GameDTO {
    private Long id;
    private String homeTeam;
    private String awayTeam;
    private String location;
    private LocalDate scheduledDate;

    public GameDTO(Long id, String homeTeam, String awayTeam, String location, LocalDate scheduledDate) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.location = location;
        this.scheduledDate = scheduledDate;
    }

    // get and set
    public Long getId() { return id; }
    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public String getLocation() { return location; }
    public LocalDate getScheduledDate() { return scheduledDate; }
}
