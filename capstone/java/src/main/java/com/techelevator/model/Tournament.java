package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tournament {

    @JsonProperty("tournament_id")
    private int tournamentId;
    private String name;
    private LocalDate date;
    private String location;
    private BigDecimal fee;
    private String description;
    private String prize;
    //private int host;
    //private boolean completed;

    public Tournament() {}

    public Tournament(int tournamentId, String name, LocalDate date, String location, BigDecimal fee, String description, String prize/*, int host, boolean completed*/) {
        this.tournamentId = tournamentId;
        this.name = name;
        this.date = date;
        this.location = location;
        this.fee = fee;
        this.description = description;
        this.prize = prize;
        //this.host = host;
        //this.completed = completed;
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

    /*
    public int getHost() {return host;}

    public void setHost(int host) {this.host = host;}

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }*/
}
