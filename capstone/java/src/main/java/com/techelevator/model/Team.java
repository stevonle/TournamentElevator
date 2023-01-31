package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {

    @JsonProperty("team_id")
    private int teamId;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("accepting_members")
    private boolean acceptingMembers;
    @JsonProperty("team_captain_id")
    private int teamCaptainId;
    @JsonProperty("games_played")
    private int gamesPlayed;

    public Team(int teamId, String teamName, boolean acceptingMembers, int teamCaptainId, int gamesPlayed) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.acceptingMembers = acceptingMembers;
        this.teamCaptainId = teamCaptainId;
        this.gamesPlayed = gamesPlayed;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public boolean isAcceptingMembers() {
        return acceptingMembers;
    }

    public void setAcceptingMembers(boolean acceptingMembers) {
        this.acceptingMembers = acceptingMembers;
    }

    public int getTeamCaptainId() {
        return teamCaptainId;
    }

    public void setTeamCaptainId(int teamCaptainId) {
        this.teamCaptainId = teamCaptainId;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
