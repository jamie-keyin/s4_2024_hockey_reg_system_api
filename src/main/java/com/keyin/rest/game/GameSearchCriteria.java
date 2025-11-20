package com.keyin.rest.game;

public enum GameSearchCriteria {
    LOCATION("location"),
    DATE("date"),
    HOME_TEAM_ID("homeTeamId"),
    AWAY_TEAM_ID("awayTeamId"),
    HOME_TEAM_NAME("homeTeamName"),
    AWAY_TEAM_NAME("awayTeamName");

    private final String key;

    GameSearchCriteria(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
