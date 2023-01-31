package com.techelevator.dao;

import com.techelevator.model.Team;

import java.util.List;

public interface TeamDao {


    boolean create(String teamName, boolean isAcceptingMembers,int TeamCaptainId, int gamesPlayed);

}
