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

  updateTeam(teamID, team){
    return axios.put(`/teams/${teamID}/update`, team);
  },

  joinRequestTeam(teamID){
    return axios.post(`/teams/${teamID}/request`)
  },
  updateTournament(id, tournament) {
    return axios.put(`/tournaments/update/${id}`, tournament)
  },
  deleteTournament(id) {
    return axios.delete(`/tournaments/delete/${id}`)
  },

  getTeamMembers(teamID){
    return axios.get(`/teams/${teamID}/members`)
  },
};
