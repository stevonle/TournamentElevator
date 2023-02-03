package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<User> listTeamMembers(int team_id) {
        List<User> teamMembers = new ArrayList<>();
        String sql = "Select * From users as u\n" +
                "INNER JOIN user_teams as ut\n" +
                "ON u.user_id = ut.user_id Where team_id = ? AND isaccepted = TRUE;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, team_id);
        while (results.next()){
            User user = mapRowToUser(results);
            teamMembers.add(user);
        }
        return teamMembers;
    }

    @Override
    public List<User> pendingTeamMembers(int team_id) {
        List<User> teamMembers = new ArrayList<>();
        String sql = "Select * From users as u\n" +
                "INNER JOIN user_teams as ut\n" +
                "ON u.user_id = ut.user_id Where team_id = ? AND isaccepted = false;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, team_id);
        while (results.next()){
            User user = mapRowToUser(results);
            teamMembers.add(user);
        }
        return teamMembers;
    }

        private User mapRowToUser(SqlRowSet rs) {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password_hash"));
            user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
            user.setActivated(true);
            return user;
        }



}
