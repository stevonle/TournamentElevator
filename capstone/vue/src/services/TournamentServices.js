import axios from 'axios';

export default {

    addTeam(team){
      return axios.post('/team/create', team)
    },

    
  
  }