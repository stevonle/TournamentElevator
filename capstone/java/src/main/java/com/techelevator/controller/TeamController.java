package com.techelevator.controller;

import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.UserTeamsDao;
import com.techelevator.model.Team;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/teams/")
public class TeamController {
    private TeamDao dao;
    private UserTeamsDao UserTeamsDao;


    public TeamController(TeamDao dao, UserTeamsDao userTeamsDao){
        this.dao = dao;
        this.UserTeamsDao = userTeamsDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Team> list(){
        return dao.list();
    }

   @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Team getTeam(@PathVariable int id){
        Team team = dao.getTeam(id);
        return team;
    }



    @RequestMapping(path = "create", method = RequestMethod.POST)
    public boolean create(@RequestBody Team team) {
       boolean success = false;
       try {
           dao.create(team.getTeamName(), team.isAcceptingMembers(), team.getTeamCaptainId(), team.getTeamDescription());
           success = true;
       } catch (Exception e) {
           System.out.println(e.getMessage() + "Something messed up");
       }
        return success;
    }

    @PutMapping("{id}")
    public void updateTeam(@PathVariable int id, @RequestBody Team team){
        team.setTeamId(id);
        boolean updated = dao.update(team);
        if(!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found!" );
        }
    }

    @PutMapping("{id}/update")
    public void update(@PathVariable int id, @RequestBody Team team){
        team.setTeamId(id);
        boolean updated = dao.update(team);
        if(!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not updated.");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("{id}/request")
    public boolean add(@PathVariable int id, Principal principal){
        boolean isAdded = false;
        Team team = dao.getTeam(id);
        try{
            System.out.println(team.toString());
            System.out.println(principal.toString());
            UserTeamsDao.add(team, principal.getName());
            isAdded = true;
        }catch (Exception e){
            System.out.println(e.getMessage() + "Something went wrong.");
        }
        return isAdded;
    }


}
