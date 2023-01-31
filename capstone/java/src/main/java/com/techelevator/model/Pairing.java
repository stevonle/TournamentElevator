package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pairing {

    @JsonProperty("pairing_id")
    private int pairingId;
    @JsonProperty("team_one")
    private int teamOneId;
    @JsonProperty("team_two")
    private int teamTwoId;
    @JsonProperty("team_one_wins")
    private int teamOneWins;
    @JsonProperty("team_two_wins")
    private int teamTwoWins;

    public Pairing(int pairingId, int teamOneId, int teamTwoId, int teamOneWins, int teamTwoWins) {
        this.pairingId = pairingId;
        this.teamOneId = teamOneId;
        this.teamTwoId = teamTwoId;
        this.teamOneWins = teamOneWins;
        this.teamTwoWins = teamTwoWins;
    }

    public int getPairingId() {
        return pairingId;
    }

    public void setPairingId(int pairingId) {
        this.pairingId = pairingId;
    }

    public int getTeamOneId() {
        return teamOneId;
    }

    public void setTeamOneId(int teamOneId) {
        this.teamOneId = teamOneId;
    }

    public int getTeamTwoId() {
        return teamTwoId;
    }

    public void setTeamTwoId(int teamTwoId) {
        this.teamTwoId = teamTwoId;
    }

    public int isTeamOneWins() {
        return teamOneWins;
    }

    public void setTeamOneWins(int teamOneWins) {
        this.teamOneWins = teamOneWins;
    }

    public int isTeamTwoWins() {
        return teamTwoWins;
    }

    public void setTeamTwoWins(int teamTwoWins) {
        this.teamTwoWins = teamTwoWins;
    }
}
