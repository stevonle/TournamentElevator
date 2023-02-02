package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class JdbcUserTeamDao implements UserTeamsDao{

    private final JdbcTemplate jdbcTemplate;
    private final UserDao UserDao;


    public JdbcUserTeamDao(JdbcTemplate jdbcTemplate, JdbcUserDao jdbcUserDao){this.jdbcTemplate = jdbcTemplate;
    this.UserDao = jdbcUserDao;}



    @Override
    public boolean add(Team team, String userName){
        boolean isAdded = false;
        String sql = "INSERT INTO user_teams(user_id, team_id, isaccepted) VALUES (?,?,FALSE);";
        User user = UserDao.findByUsername(userName);

        jdbcTemplate.update(sql, user.getId(), team.getTeamId() );

        return isAdded;
    }

}
