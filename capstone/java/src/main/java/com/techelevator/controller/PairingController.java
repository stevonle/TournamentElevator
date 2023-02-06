package com.techelevator.controller;

import com.techelevator.dao.PairingDao;
import com.techelevator.model.Pairing;
import com.techelevator.model.Team;
import com.techelevator.model.Tournament;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pairings")
public class PairingController {
    private PairingDao dao;

    public PairingController(PairingDao dao){
        this.dao = dao;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Pairing create(@RequestBody Pairing pairing){
        Pairing createdPairing = dao.create(pairing);
        return createdPairing;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        dao.delete(id);
    }

    @RequestMapping(path = "/{tournamentId}", method = RequestMethod.GET)
    public List<Pairing> getPairingsForTournament(@PathVariable int tournamentId){
        return dao.list(tournamentId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
    public boolean update(@PathVariable int id, @RequestBody Pairing pairing) {
        boolean success = false;
        try {
            dao.update(pairing);
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - Match update failed!!!!!");
        }
        return success;
    }

}
