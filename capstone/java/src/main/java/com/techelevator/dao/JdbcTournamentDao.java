package com.techelevator.dao;

import com.techelevator.model.Tournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTournamentDao implements TournamentDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean create(Tournament tournament, int hostId) {
        String insertTournamentSql = "INSERT INTO tournaments (tournament_name, game_type, tournament_date, " +
                                     "tournament_location, fee, tournament_description, prize, host) " +
                                     "VALUES (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(insertTournamentSql, tournament.getName(), tournament.getDate(),
                tournament.getLocation(), tournament.getFee(), tournament.getDescription(),
                tournament.getPrize(), hostId)==1;
    }

    @Override
    public Tournament getTournamentById(int tournamentId) {
        Tournament tournament = null;
        String selectTournamentSql = "SELECT * " +
                "FROM tournaments WHERE tournament_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(selectTournamentSql, tournamentId);
        if (results.next()) {
            tournament = mapRowToTournament(results);
        }
        return tournament;
    }

    @Override
    public List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = new ArrayList<>();
        String allTournamentsSql = "SELECT * FROM tournaments;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(allTournamentsSql);
        while (results.next()) {
            Tournament tournament = mapRowToTournament(results);
            tournaments.add(tournament);
        }
        return tournaments;
    }



    private Tournament mapRowToTournament(SqlRowSet rs) {
        Tournament tournament = new Tournament();
        tournament.setTournamentId(rs.getInt("tournament_id"));
        tournament.setName(rs.getString("tournament_name"));
        tournament.setGameType(rs.getInt("game_type"));
        tournament.setDate(rs.getDate("tournament_date").toLocalDate());
        tournament.setLocation(rs.getString("tournament_location"));
        tournament.setFee(rs.getBigDecimal("fee"));
        tournament.setDescription(rs.getString("tournament_description"));
        tournament.setPrize(rs.getString("prize"));
        tournament.setHost(rs.getInt("host"));
        //tournament.setCompleted(rs.getBoolean("completed"));
        return tournament;
    }


}
