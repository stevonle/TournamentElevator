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
    public boolean create(String teamName, boolean acceptingMembers, int teamCaptainId, String teamDescription) {
        String insertTeamSql = "insert into teams(team_name, isAcceptingMembers, team_captain, team_description) values (?,?,?,?);";


        return jdbcTemplate.update(insertTeamSql, teamName, acceptingMembers, teamCaptainId, teamDescription)==1;
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
