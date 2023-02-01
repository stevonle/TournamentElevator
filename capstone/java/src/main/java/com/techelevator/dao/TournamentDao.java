package com.techelevator.dao;

import com.techelevator.model.Tournament;

public interface TournamentDao {
    boolean create(Tournament tournament, int hostId);

    
}
