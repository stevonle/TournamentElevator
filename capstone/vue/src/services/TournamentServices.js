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
  }
};
