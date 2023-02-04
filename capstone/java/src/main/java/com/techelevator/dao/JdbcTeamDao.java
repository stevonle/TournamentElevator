package com.techelevator.dao;

import com.techelevator.model.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTeamDao implements TeamDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTeamDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Team> list(){
        List<Team> teams = new ArrayList<>();
        String getAllTeamsSql = "SELECT * FROM teams;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(getAllTeamsSql);
        while(results.next()) {
               teams.add(mapRowToTeam(results));
        }
        return teams;
    }

    @Override
    public Team getTeam(int id) {
        Team team = null;
        String sql = "SELECT * from teams WHERE team_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()){
            team = mapRowToTeam(results);
        }
        return team;
    }

    @Override
    public boolean update(Team team) {
        boolean isUpdated = false;
        String sql = "UPDATE teams SET team_name = ?, isacceptingmembers = ?," +
                "team_captain = ?, team_description = ? WHERE team_id = ?";

        if(jdbcTemplate.update(sql, team.getTeamName(), team.isAcceptingMembers(),
                team.getTeamCaptainId(), team.getTeamDescription(), team.getTeamId())==1){
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public Team create(Team team) {
        String insertTeamSql = "insert into teams(team_name, isAcceptingMembers, team_captain, team_description) values (?,?,?,?) RETURNING team_id";
        int id = jdbcTemplate.queryForObject(insertTeamSql, Integer.class, team.getTeamName(), team.isAcceptingMembers(), team.getTeamCaptainId(), team.getTeamDescription());
        team.setTeamId(id);
        return team;
    }

    private Team mapRowToTeam(SqlRowSet rowSet){
        Team team = new Team();
        team.setTeamId(rowSet.getInt("team_id"));
        team.setTeamName(rowSet.getString("team_name"));
        team.setAcceptingMembers(rowSet.getBoolean("isacceptingmembers"));
        team.setTeamCaptainId(rowSet.getInt("team_captain"));
        team.setTeamDescription(rowSet.getString("team_description"));
        return team;
    }


}
