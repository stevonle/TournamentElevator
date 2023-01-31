import axios from 'axios';

export default {

    addTeam(team){
      return axios.post('/team/create', team)
    },

    addTournament(tournament) {
      return axios.post('/tournament/create', tournament)
    }

    
  
  }