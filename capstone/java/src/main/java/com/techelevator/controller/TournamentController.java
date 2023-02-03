package com.techelevator.controller;

import com.techelevator.dao.TournamentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Team;
import com.techelevator.model.Tournament;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tournaments")
public class TournamentController {
    private TournamentDao dao;
    private UserDao userDao;

    public TournamentController(TournamentDao dao, UserDao userDao){
        this.dao = dao;
        this.userDao = userDao;
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Created")
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean create(@RequestBody Tournament tournament, Principal principal){
        boolean success = false;
        try {
            int authorizedUser = userDao.findIdByUsername(principal.getName());
            dao.createTournament(tournament, authorizedUser);
            success = true;
        }catch (Exception e){
            System.out.println(e.getMessage() + " Tournament create failed!!!!!");
        }
        return success;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Tournament getTournament(@PathVariable int id) {
        return dao.getTournamentById(id);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Tournament> getTournaments() {
        return dao.getAllTournaments();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
    public boolean update(@RequestBody Tournament tournament, @PathVariable int id) {
        boolean success = false;
        try {
            dao.updateTournament(tournament, id);
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Tournament update failed!!!!!");
        }
        return success;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/{id}/join", method = RequestMethod.POST)
    public boolean join(@PathVariable int id, @RequestBody Team team) {
        boolean success = false;
        try {
            dao.joinTournament(id, team);
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Tournament join failed!!!!!");
        }
        return success;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable int id) {
        boolean success = false;
        try {
            dao.deleteTournament(id);
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Tournament delete failed!!!!!");
        }
        return success;
    }
}
