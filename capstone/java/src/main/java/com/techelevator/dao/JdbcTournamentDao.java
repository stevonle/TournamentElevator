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
        String insertTournamentSql = "insert into tournaments(tournament_name, game_type, tournament_date, tournament_location, fee, tournament_description, prize, host) values (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(insertTournamentSql,tournament.getGameType(), tournament.getName(), tournament.getDate(), tournament.getLocation(), tournament.getFee(), tournament.getDescription(), tournament.getPrize(), tournament.getHost())==1;
    }

}
