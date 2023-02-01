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
      v-model="team.teamName"
    /></div>
    
    <div class='mb-2'>
    <label for="acceptingMembers" class="form-label"> Accepting Members? </label>
    <input
      class="team-input"
      type="checkbox"
      placeholder="acceptingMembers"
      v-model="team.acceptingMembers"
    />
    </div>
    <div>
    <label for="teamDescription" class='form-label'> Please enter team description </label>
    <textarea type="text" class='team-input' placeholder="Team Description" v-model="team.teamDescription"/>
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
  data() {
    return {
      team: {
        teamName: "", 
        acceptingMembers: false,
        teamCaptainId:"",
        teamDescription: "",
      },
    };
  },
  methods: {
   async saveTeam() {
    const tempUser = this.$store.state.user
    this.team.teamCaptainId = tempUser.id;
      try {
        const response = await TournamentServices.addTeam(
          this.team
        );
        if (response.status !== 200) {
          console.log(response.statusText);
          return;
        }

         this.$store.commit("SAVE_TEAM", this.team);
         this.$router.push('/')
      } catch (err) {
        console.log(err);
      }
    },
  },
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