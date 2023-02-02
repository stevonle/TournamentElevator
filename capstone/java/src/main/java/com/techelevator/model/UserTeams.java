package com.techelevator.model;

public class UserTeams {
    private int user_id;
    private int team_id;
    private boolean isAccepted;

    UserTeams(int user_id, int team_id, boolean isAccepted){
        this.user_id = user_id;
        this.team_id = team_id;
        this.isAccepted = isAccepted;
    }
    UserTeams(){}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
