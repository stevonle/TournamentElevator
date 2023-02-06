package com.techelevator.dao;

import com.techelevator.model.Pairing;

import java.util.List;

public interface PairingDao {
     Pairing create(Pairing pairing);
     List<Pairing> list(int tournamentId);
     void delete(int id);
     void update(Pairing pairing);
}
