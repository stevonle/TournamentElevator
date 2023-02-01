package com.techelevator.dao;

import com.techelevator.model.Tournament;

import java.util.List;

public interface TournamentDao {
    boolean create(Tournament tournament, int hostId);
    Tournament getTournamentById(int tournamentId);
    List<Tournament> getAllTournaments();
    
}
