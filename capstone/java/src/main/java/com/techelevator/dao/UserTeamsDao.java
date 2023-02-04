package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface UserTeamsDao {

    boolean add(Team team, String userName, boolean isaccepted);

    List<User> listTeamMembers(int team_id);

    List<User> pendingTeamMembers(int id);

    boolean acceptMember(int teamId, int userId);

    void rejectMember(int teamId, int userId);
}
