package com.techelevator.dao;

import com.techelevator.model.Team;

import java.security.Principal;

public interface UserTeamsDao {

    boolean add(Team team, String userName);
}
