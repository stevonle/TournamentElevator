package com.techelevator.controller;

import com.techelevator.dao.TournamentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Tournament;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin

@RequestMapping("/tournament/")
public class TournamentController {
    private TournamentDao dao;
    private UserDao userDao;

    public TournamentController(TournamentDao dao, UserDao userDao){
        this.dao = dao;
        this.userDao = userDao;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "create", method = RequestMethod.POST)
    public boolean create(@RequestBody Tournament tournament, Principal principal){
        boolean success = false;
        try {
            int authorizedUser = userDao.findIdByUsername(principal.getName());
            dao.create(tournament, authorizedUser);
            success = true;
        }catch (Exception e){
            System.out.println(e.getMessage() + "Tournament error");
        }
        return success;
    }

    @RequestMapping(path = "tournaments/tournament/{id}", method = RequestMethod.GET)
    public Tournament getTournament(@PathVariable int tournamentId) {
        return dao.getTournamentById(tournamentId);
    }


}
