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
    public boolean add(Team team, String userName, boolean isaccepted){
        boolean isAdded = false;
        String sql = "INSERT INTO user_teams(user_id, team_id, isaccepted) VALUES (?,?,?);";
        User user = UserDao.findByUsername(userName);

        jdbcTemplate.update(sql, user.getId(), team.getTeamId(), isaccepted);

        return isAdded;
    }

    @Override
    public List<User> listTeamMembers(int team_id) {
        List<User> teamMembers = new ArrayList<>();
        String sql = "Select * From users as u\n" +
                "INNER JOIN user_teams as ut\n" +
                "ON u.user_id = ut.user_id\n" +
                "Where team_id = ? AND isaccepted = TRUE;";
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

    @Override
    public boolean acceptMember(int teamId, int userId){
        boolean accepted = false;
        String sql = "UPDATE user_teams SET isaccepted = TRUE WHERE user_id=? AND team_id=?;";
    if(jdbcTemplate.update(sql, userId, teamId)==1){
        accepted= true;
        }
        return accepted;
    }

    @Override
    public void rejectMember(int teamId, int userId) {
        String sql = "DELETE FROM user_teams WHERE user_id = ? AND team_id = ?;";
        jdbcTemplate.update(sql, userId, teamId);
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
