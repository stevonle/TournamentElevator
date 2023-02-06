import axios from "axios";

export default {
  addTeam(team) {
    return axios.post("/teams/create", team);
  },

  addTournament(tournament) {
    return axios.post("/tournaments/create", tournament);
  },

  viewAllTournaments() {
    return axios.get("/tournaments/all");
  },

  viewAllTeams() {
    return axios.get("/teams/");
  },

  getTeamById(teamID) {
    return axios.get(`/teams/${teamID}`);
  },

  getTournamentById(id) {
    return axios.get(`/tournaments/${id}`);
  },

  updateTeam(teamID, team) {
    return axios.put(`/teams/${teamID}/update`, team);
  },

  joinRequestTeam(teamID) {
    return axios.post(`/teams/${teamID}/request`);
  },
  updateTournament(id, tournament) {
    return axios.put(`/tournaments/update/${id}`, tournament);
  },
  deleteTournament(id) {
    return axios.delete(`/tournaments/delete/${id}`);
  },

  getTeamMembers(teamID) {
    return axios.get(`/teams/${teamID}/members`);
  },
  getPendingMembers(teamID) {
    return axios.get(`/teams/${teamID}/pending`);
  },
  addMemberToTeam(teamID, userID) {
    return axios.put(`/teams/${teamID}/pending/${userID}`);
  },
  rejectMemberFromTeam(teamID, userID) {
    return axios.delete(`/teams/${teamID}/pending/${userID}`);
  },

  acceptTeamForTournament(tournamentId, teamId) {
    return axios.put(`/tournaments/${tournamentId}/accept/${teamId}`);
  },
  rejectTeamForTournament(tournamentId, teamId) {
    return axios.delete(`/tournaments/${tournamentId}/reject/${teamId}`);
  },

  joinTournament(id, team) {
    return axios.post(`/tournaments/${id}/join`, team);
  },

  addPairing(pairing) {
    return axios.post("/pairings/create", pairing);
  },
  getPairingsForTournament(tournamentId) {
    return axios.get(`/pairings/${tournamentId}`);
  },
  deletePairing(pairingId) {
    return axios.delete(`/pairings/${pairingId}`);
  },
  updatePairings(id, pairing) {
    return axios.put(`/pairings/update/${id}`, pairing);
  },
};
