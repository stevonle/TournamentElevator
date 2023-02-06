package com.techelevator.controller;

import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.UserTeamsDao;
import com.techelevator.model.Team;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
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
    private UserDao UserDao;


    public TeamController(TeamDao dao, UserTeamsDao userTeamsDao, UserDao userDao){
        this.dao = dao;
        this.UserTeamsDao = userTeamsDao;
        this.UserDao = userDao;
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


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "create", method = RequestMethod.POST)
    public boolean create(@RequestBody Team team, Principal principal) {
       boolean success = false;
       try {
           dao.create(team);
           UserTeamsDao.add(team, principal.getName(), true);
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
            UserTeamsDao.add(team, principal.getName(), false);
            isAdded = true;
        }catch (Exception e){
            System.out.println(e.getMessage() + "Something went wrong.");
        }
        return isAdded;
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}/members")
    public List<User> listTeamMembers(@PathVariable int id){
        return UserTeamsDao.listTeamMembers(id);
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}/pending")
    public List<User> pendingTeamMembers(@PathVariable int id){
        return UserTeamsDao.pendingTeamMembers(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("{teamId}/pending/{userId}")
    public boolean acceptMember(@PathVariable int teamId,@PathVariable int userId){
        return UserTeamsDao.acceptMember(teamId, userId);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("{teamId}/pending/{userId}")
    public void rejectMember(@PathVariable int teamId,@PathVariable int userId){
        UserTeamsDao.rejectMember(teamId, userId);
    }


}
