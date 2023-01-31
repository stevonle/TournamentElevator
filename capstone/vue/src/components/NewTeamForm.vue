<template>
  <form class="new-team-form" v-on:submit.prevent="saveTeam">
      <label for="teamNameInput" class='form-label'> Input Team Name Below </label>
    <input
      class="team-name-input"
      type="text"
      placeholder="Team Name"
      id="teamNameInput"
      v-model="team.teamName"
    />
    Accepting New Members?
    <br>
    <input
      class="accepting-members-input"
      type="checkbox"
      placeholder="acceptingMembers"
      v-model="team.acceptingMembers"
    />
    <br>
    <button class="btn btn-md btn-primary btn-block" type='submit' placeholder="createTeamButton">Create</button>
  </form>
</template>

<script>
// import axios from 'axios'; uncomment when line 47 implemented
import TournamentServices from '../services/TournamentServices.js'

export default {
  name: "new-team-form",
  data() {
    return {
      team: {
        teamName: "", 
        acceptingMembers: false,
        teamCaptainUsername: this.$store.state.user.username,
        games_played: 0,
      },
    };
  },
  methods: {
    saveTeam() {
//     axios.get('api/user').then(response => {
//    team.teamCaptainId = response.userId
//wait for controller to add get user info



      this.$store.commit("SAVE_TEAM", this.team);
      this.team = {
        teamName: "",
        acceptingMembers: "",
        teamCaptainUsername: this.$store.data.user,
        games_played: 0,
      };



    TournamentServices
        .addTeam(this.team)
        .then(response => {
            if(response.status === 201){
                this.$router.push('/');
                //redirect to my-teams page when done
            }
        });
    


    },
  },
};
</script>

<style scoped>

.new-team-form {
  color: orange;
  margin: 75px auto;
  width: 50%;
  text-align: center;
  min-width: 300px;
  max-width: 400px;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr 1fr 1fr .5fr;
  grid-template-areas: 'label'
  'teamName'
  'acceptingMembers'
  'createTeamButton';
}
.team-name-input{
    display:block;
}
.accepting-members-input {
    display: block;
}

</style>