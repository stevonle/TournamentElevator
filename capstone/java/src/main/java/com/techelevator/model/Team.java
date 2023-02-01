package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {

    @JsonProperty("team_id")
    private int teamId;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("isAcceptingMembers")
    private boolean acceptingMembers;
    @JsonProperty("team_captain")
    private int teamCaptainId;
    @JsonProperty("team_description")
    private String teamDescription;

    public Team(){}



    public Team(int teamId, String teamName, boolean acceptingMembers, int teamCaptainId, String teamDescription) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.acceptingMembers = acceptingMembers;
        this.teamCaptainId = teamCaptainId;
        this.teamDescription = teamDescription;
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

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }


}
