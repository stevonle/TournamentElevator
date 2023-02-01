package com.techelevator.dao;

import com.techelevator.model.Tournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTournamentDao implements TournamentDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean create(Tournament tournament, int hostId) {
        String insertTournamentSql = "INSERT INTO tournaments (tournament_name, tournament_date, " +
                                     "tournament_location, fee, tournament_description, prize, host) " +
                                     "VALUES (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(insertTournamentSql, tournament.getName(), tournament.getDate(),
                tournament.getLocation(), tournament.getFee(), tournament.getDescription(),
                tournament.getPrize(), hostId)==1;
    }
}
