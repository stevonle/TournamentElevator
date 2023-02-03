package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.Tournament;

import java.util.List;

public interface TournamentDao {
    boolean createTournament(Tournament tournament, int hostId);
    Tournament getTournamentById(int tournamentId);
    List<Tournament> getAllTournaments();
    boolean updateTournament(Tournament tournament, int id);
    boolean joinTournament(int tournamentId, Team team);
    
}
