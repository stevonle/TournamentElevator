package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTeamDao implements TeamDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTeamDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean create(String teamName, boolean isAcceptingMembers, int teamCaptainId, int gamesPlayed) {
        String insertTeamSql = "insert into teams(team_name, isAcceptingMembers, team_captain) values (?,?,?)";

        return jdbcTemplate.update(insertTeamSql, teamName, isAcceptingMembers, teamCaptainId)==1;
    }
}
