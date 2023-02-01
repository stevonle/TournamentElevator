package com.techelevator.dao;

import com.techelevator.model.Tournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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

    @Override
    public Tournament getTournamentById(int tournamentId) {
        Tournament tournament = null;
        String selectTournamentSql = "SELECT tournament_name, tournament_date, tournament_location, fee, tournament_description, prize, host) " +
                "FROM tournaments WHERE tournament_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(selectTournamentSql, tournamentId);
        if (results.next()) {
            tournament = mapRowToTournament(results);
        }
        return tournament;
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
        //tournament.setCompleted(rs.getBoolean("completed"));
        return tournament;
    }


}
