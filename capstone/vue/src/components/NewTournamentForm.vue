<template>
  <form class="new-tournament-form" v-on:submit.prevent="saveTournament">
    <input
      class="tournament-input"
      type="text"
      placeholder="Tournament Name"
      v-model="tournament.name"
    />
    <input
      class="tournament-input"
      type="date"
      placeholder="Tournament date"
      v-model="tournament.date"
    />
    <input
      class="tournament-input"
      type="text"
      placeholder="Tournament location"
      v-model="tournament.location"
    />
    <input
      class="tournament-input"
      type="number"
      placeholder="Tournament fee"
      v-model="tournament.fee"
    />
    <textarea
      class="tournament-input"
      type="text"
      placeholder="Tournament description"
      v-model="tournament.description"
    />
    <input
      class="tournament-input"
      type="number"
      placeholder="Tournament prize"
      v-model="tournament.prize"
    />

    <button
      class="btn btn-md btn-primary btn-block"
      type="submit"
      placeholder="createTeamButton"
    >
      Create
    </button>
  </form>
</template>

<script>
// import axios from 'axios'; uncomment when line 47 implemented
import TournamentServices from "../services/TournamentServices.js";

export default {
  name: "new-tournament-form",
  data() {
    return {
      tournament: {
        name: "",
        date: "",
        location: "",
        fee: 0,
        description: "",
        prize: 0,
      },
    };
  },
  methods: {
    async saveTournament() {
      try {
        const response = await TournamentServices.addTournament(
          this.tournament
        );
        if (response.status !== 200) {
          console.log(response.statusText);
          return;
        }

         this.$store.commit("SAVE_TOURNAMENT", this.tournament);
         this.$router.push('/')
      } catch (err) {
        console.log(err);
      }
    },
  },
};
</script>

<style scoped>
.new-tournament-form {
  color: orange;
  margin: 75px auto;
  width: 50%;
  text-align: center;
  min-width: 300px;
  max-width: 400px;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr 1fr 1fr 0.5fr;
  grid-template-areas:
    "label"
    "teamName"
    "acceptingMembers"
    "createTeamButton";
}
.tournament-input {
  display: block;
}
.accepting-members-input {
  display: block;
}
</style>
