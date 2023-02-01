package com.techelevator.controller;

import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Team;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/teams/")
public class TeamController {
    private TeamDao dao;
    private UserDao userDao;

    public TeamController(TeamDao dao, UserDao userDao){
        this.dao = dao;
        this.userDao = userDao;
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


}
