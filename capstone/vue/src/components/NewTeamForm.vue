<template>
  <form class="new-team-form" v-on:submit.prevent="saveTeam">
      <label for="teamNameInput" class='form-label'> Input Team Name Below </label>
    <input
      class="team-name-input"
      type="text"
      placeholder="Team Name"
      id="teamNameInput"
      v-model="team.team_name"
    />
    Accepting New Members?
    <br>
    <input
      class="accepting-members-input"
      type="checkbox"
      placeholder="acceptingMembers"
      v-model="team.accepting_members"
    />
    <br>
    <label for="teamDescription" class='form-label'> Please enter team description </label>
    <input type="text" class='team-description-input' placeholder="Team Description" v-model="team.teamDescription"/>
    <button class="btn btn-md btn-primary btn-block" type='submit' placeholder="createTeamButton">Create</button>
  </form>
</template>

<script>

import TournamentServices from '../services/TournamentServices.js'

export default {
  name: "new-team-form",
  data() {
    return {
      team: {
        team_name: "", 
        accepting_members: false,
        team_captain_id:"",
        teamDescription: "",
      },
    };
  },
  methods: {
   async saveTeam() {
    const tempUser = this.$store.state.user
    this.team.team_captain_id = tempUser.id;
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
  color: orange;
  margin: 75px auto;
  width: 50%;
  text-align: center;
  min-width: 300px;
  max-width: 400px;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr 1fr 1fr .5fr 2fr;
  grid-template-areas: 'label'
  'teamName'
  'acceptingMembers'
  'createTeamButton'
  'teamDescription';
}
.team-name-input{
    display:block;
}
.accepting-members-input {
    display: block;
}

</style>