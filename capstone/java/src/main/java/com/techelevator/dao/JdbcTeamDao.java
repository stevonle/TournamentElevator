package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTeamDao implements TeamDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTeamDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean create(String teamName, boolean isAcceptingMembers, int teamCaptainId, String teamDescription) {
        String insertTeamSql = "insert into teams(team_name, isAcceptingMembers, team_captain, team_description) values (?,?,?,?)";

        return jdbcTemplate.update(insertTeamSql, teamName, isAcceptingMembers, teamCaptainId, teamDescription)==1;
    }
}
