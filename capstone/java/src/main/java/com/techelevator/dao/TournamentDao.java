package com.techelevator.dao;

import com.techelevator.model.Invite;
import com.techelevator.model.Team;
import com.techelevator.model.Tournament;

import java.util.List;

public interface TournamentDao {
    boolean createTournament(Tournament tournament, int hostId);
    Tournament getTournamentById(int tournamentId);
    List<Invite> getTournamentInvitesById(int tournamentId);
    List<Tournament> getAllTournaments();
    boolean updateTournament(int id, Tournament tournament);
    boolean joinTournament(int tournamentId, Team team);
    boolean deleteTournament(int tournamentId);
    boolean acceptTeam(int tournamentId, int teamId);
    boolean rejectTeam(int tournamentId, int teamId);
}
