<template>
<div>
  <h1> Create Team </h1>
  <form class="new-team-form" v-on:submit.prevent="saveTeam">
      <label for="teamNameInput" class='form-label'> Input Team Name Below </label>
      <div class='mb-2'>
    <input
      class="team-input"
      type="text"
      placeholder="Team Name"
      id="teamNameInput"
      v-model="team.team_name"
    /></div>
    
    <div class='mb-2'>
    <label for="acceptingMembers" class="form-label"> Accepting Members? </label>
    <input
      class="team-input"
      type="checkbox"
      placeholder="acceptingMembers"
      v-model="team.isAcceptingMmembers"
    />
    </div>
    <div>
    <label for="teamDescription" class='form-label'> Please enter team description </label>
    <textarea type="text" class='team-input' placeholder="Team Description" v-model="team.team_description"/>
    </div>
    <br />
    <button class="btn btn-md btn-primary btn-block" type='submit' placeholder="createTeamButton">Create</button>
  </form>
  </div>
</template>

<script>

import TournamentServices from '../services/TournamentServices.js'

export default {
  name: "new-team-form",
  props: {
    teamID: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      team: {
        team_name: "", 
        isAcceptingMembers: false,
        team_captain:"",
        team_description: "",
      },
    };
  },
  methods: {
   saveTeam() {
     const newTeam = {
       team_id: Number(this.$route.params.teamID),
       team_name: this.team.team_name,
       isAcceptingMembers: this.team.isAcceptingMembers,
       team_captain: this.team.team_captain,
       team_description: this.team.team_description
   };
   if(this.teamID === 0){
     TournamentServices.addTeam(newTeam).then(response => {
       if(response.status === 201) {
         this.$router.push(`/teams/all`);
       }
     })
    
     } else {
       newTeam.team_id = this.teamID;
       newTeam.team_name = this.team.team_name;
       newTeam.isAcceptingMembers = this.team.isAcceptingMembers;
       newTeam.team_captain = this.team.team_captain;
       newTeam.team_description = this.team.team_description;
       TournamentServices.updateTeam(newTeam).then(response => {
         if(response.status === 200){
           this.$router.push(`/teams/all`);
         }
       })
     }
   },
  },
   
  created(){
    if(this.teamID != 0){
      TournamentServices.getTeamById(this.teamID).then(response => {
        this.card = response.data;
      })
      .catch(error => {
        if(error.response && error.response.status === 404){
          alert(
            "Team not available. You have entered an invalid team ID pathway."
          );
          this.$router.push('/');
        }
      });
    }
  }
};
</script>

<style scoped>

.new-team-form {
  color: rgb(241, 179, 63);
  margin: 35px auto;
  width: 100%;
  min-width: 300px;
  max-width: 400px;
  display: grid;
}

.team-input{
display: block;
width: 100%;
}

h1 {
  color: orange;
  text-align: center;
  margin: 20px 0px 0px 0px;
}

</style>