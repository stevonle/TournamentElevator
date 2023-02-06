package com.techelevator.dao;

import com.techelevator.model.Pairing;
import com.techelevator.model.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPairingDao implements PairingDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPairingDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Pairing create(Pairing pairing) {
        String insertPairingSql = "insert into pairings(tournament_id, teamone, teamtwo, team_one_wins, team_two_wins, round) values (?,?,?,?,?,?) RETURNING pairing_id";
        int id = jdbcTemplate.queryForObject(insertPairingSql, Integer.class, pairing.getTournamentId(), pairing.getTeamOneId(), pairing.getTeamTwoId(), 0, 0, pairing.getRound());
        pairing.setPairingId(id);
        return pairing;
    }

    @Override
    public void delete(int id) {
        String deletePairingSql = "DELETE from pairings WHERE pairing_id = ?";
        jdbcTemplate.update(deletePairingSql, id);
    }

    @Override
    public void update(Pairing pairing) {
        String updatePairingSql = "UPDATE pairings SET teamone = ?, teamtwo = ?, " +
                "team_one_wins = ?, team_two_wins = ?, round = ? " +
                "WHERE tournament_id = ? AND pairing_id = ?;";


         jdbcTemplate.update(updatePairingSql, pairing.getTeamOneId(), pairing.getTeamTwoId(), pairing.getTeamOneWins(), pairing.getTeamTwoWins(), pairing.getRound(), pairing.getTournamentId(), pairing.getPairingId());
    }

    @Override
    public List<Pairing> list(int tournamentId) {
        List<Pairing> pairings = new ArrayList<>();
        String getAllPairingsSql = "SELECT * FROM pairings WHERE tournament_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(getAllPairingsSql, tournamentId);
        while(results.next()) {
            pairings.add(mapRowToPairing(results));
        }
        return pairings;
    }

    private Pairing mapRowToPairing(SqlRowSet rowSet){
        Pairing pairing = new Pairing();
        pairing.setPairingId(rowSet.getInt("pairing_id"));
        pairing.setTeamOneId(rowSet.getInt("teamone"));
        pairing.setTeamTwoId(rowSet.getInt("teamtwo"));
        pairing.setTournamentId(rowSet.getInt("tournament_id"));
        pairing.setTeamOneWins(rowSet.getInt("team_one_wins"));
        pairing.setTeamTwoWins(rowSet.getInt("team_two_wins"));
        pairing.setRound(rowSet.getInt("round"));
        return pairing;
    }
}
