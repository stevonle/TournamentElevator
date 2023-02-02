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
        String sql = "UPDATE user_teams SET user_id = ? , team_id = ?, isAccepted = FALSE; ";
        User user = UserDao.findByUsername(userName);

        jdbcTemplate.update(sql, user.getId(), team.getTeamId() );

        return isAdded;
    }

}
