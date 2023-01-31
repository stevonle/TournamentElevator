package com.techelevator.dao;

import com.techelevator.model.Tournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTournamentDao implements TournamentDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTournamentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tournament createTournament(Tournament newTournament) {
        Tournament createdTournament = null;

        String sql = "INSERT INTO tournaments (tournament_name, tournament_date, " +
                     "tournament_location, fee, tournament_description, prize, completed) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING tournament_id;";
        int newTournamentId = jdbcTemplate.queryForObject(sql, Integer.class, newTournament.getName(),
                newTournament.getDate(), newTournament.getLocation(), newTournament.getFee(),
                newTournament.getDescription(), newTournament.getPrize(), "false");
        sql = "SELECT tournament_id, tournament_name, tournament_date, tournament_location, fee, " +
                "tournament_description, prize, completed;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newTournamentId);
        if (results.next()) {
            createdTournament = mapRowToTournament(results);
        }
        return createdTournament;
    }

    private Tournament mapRowToTournament(SqlRowSet rs) {
        Tournament tournament = new Tournament();
        tournament.setTournamentId(rs.getInt("tournament_id"));
        tournament.setName(rs.getString("name"));
        tournament.setDate(rs.getDate("date").toLocalDate());
        tournament.setLocation(rs.getString("location"));
        tournament.setFee(rs.getBigDecimal("fee"));
        tournament.setDescription(rs.getString("description"));
        tournament.setPrize(rs.getString("prize"));
        tournament.setCompleted(rs.getBoolean("completed"));
        return tournament;
    }
}
