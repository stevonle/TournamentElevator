package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tournament {

    @JsonProperty("id")
    private int tournamentId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("gametype")
    private int gameType;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("location")
    private String location;
    private BigDecimal fee;
    @JsonProperty("description")
    private String description;
    private String prize;
    private int host;
    private List<Invite> invites;
    private boolean completed;

    public Tournament() {}

    public Tournament(int tournamentId, String name, int gameType, LocalDate date, String location, BigDecimal fee, String description, String prize, int host, boolean completed) {
        this.tournamentId = tournamentId;
        this.name = name;
        this.gameType = gameType;
        this.date = date;
        this.location = location;
        this.fee = fee;
        this.description = description;
        this.prize = prize;
        this.host = host;
        this.invites = new ArrayList<>();
        this.completed = completed;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getHost() {return host;}

    public void setHost(int host) {this.host = host;}

    public List<Invite> getInvites() {
        return invites;
    }

    public void setInvites(List<Invite> invites) {
        this.invites = invites;
    }
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
