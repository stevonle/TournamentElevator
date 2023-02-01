package com.techelevator.dao;

import com.techelevator.model.Team;

import java.util.List;

public interface TeamDao {

    List<Team> list();

    Team getTeam(int id);

    boolean updateTeam(Team team);

    boolean create(String teamName, boolean isAcceptingMembers,int TeamCaptainId, String teamDescription);

}
