package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pairing {

    @JsonProperty("pairing_id")
    private int pairingId;
    @JsonProperty("tournament_id")
    private int tournamentId;
    @JsonProperty("team_one")
    private int teamOneId;
    @JsonProperty("team_two")
    private int teamTwoId;
    @JsonProperty("team_one_wins")
    private int teamOneWins;
    @JsonProperty("team_two_wins")
    private int teamTwoWins;
    @JsonProperty("round")
    private int round;

    public Pairing() {

    }

    public Pairing(int pairingId, int teamOneId, int teamTwoId, int teamOneWins, int teamTwoWins, int round) {
        this.pairingId = pairingId;
        this.teamOneId = teamOneId;
        this.teamTwoId = teamTwoId;
        this.teamOneWins = teamOneWins;
        this.teamTwoWins = teamTwoWins;
        this.round = round;
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

    public int getTeamOneWins() {
        return teamOneWins;
    }

    public void setTeamOneWins(int teamOneWins) {
        this.teamOneWins = teamOneWins;
    }

    public int getTeamTwoWins() {
        return teamTwoWins;
    }

    public void setTeamTwoWins(int teamTwoWins) {
        this.teamTwoWins = teamTwoWins;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

}
