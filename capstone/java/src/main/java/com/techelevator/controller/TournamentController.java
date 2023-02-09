package com.techelevator.controller;

import com.techelevator.dao.TournamentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Invite;
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
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean create(@RequestBody Tournament tournament, Principal principal){
        boolean success = false;
        try {
            int authorizedUser = userDao.findIdByUsername(principal.getName());
            dao.createTournament(tournament, authorizedUser);
            success = true;
        }catch (Exception e){
            System.out.println(e.getMessage() + " - Tournament create failed!!!!!");
        }
        return success;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Tournament getTournament(@PathVariable int id, Principal principal) {
        Tournament tournament = dao.getTournamentById(id);
        List<Invite> invites = dao.getTournamentInvitesById(id);
        tournament.setInvites(invites);
        return tournament;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Tournament> getTournaments() {
        return dao.getAllTournaments();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
    public boolean update(@PathVariable int id, @RequestBody Tournament tournament) {
        boolean success = false;
        try {
            dao.updateTournament(id, tournament);
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - Tournament update failed!!!!!");
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
            System.out.println(e.getMessage() + " - Tournament join failed!!!!!");
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
            System.out.println(e.getMessage() + " - Tournament delete failed!!!!!");
        }
        return success;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/{tournamentId}/accept/{teamId}", method = RequestMethod.PUT)
    public boolean accept(@PathVariable int tournamentId, @PathVariable int teamId) {
        boolean success = false;
        try {
            dao.acceptTeam(tournamentId, teamId);
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - Team accept failed!!!!!");
        }
        return success;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/{tournamentId}/reject/{teamId}", method = RequestMethod.DELETE)
    public boolean reject(@PathVariable int tournamentId, @PathVariable int teamId) {
        boolean success = false;
        try {
            dao.rejectTeam(tournamentId, teamId);
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - Team reject failed!!!!!");
        }
        return success;
    }
}
