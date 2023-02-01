import axios from 'axios';

export default {

    addTeam(team){
      return axios.post('/teams/create', team)
    },

    addTournament(tournament) {
      return axios.post('/tournament/create', tournament)
    },

    viewAllTournaments() {
      return axios.get('/tournaments')
    },

    viewAllTeams(){
      return axios.get('/teams/')
    },

    getTeamById(teamID){
      return axios.get(`/teams/${teamID}`)
    }
  
  }