<template>
  <div>
    <h1>All Tournaments</h1>
    <div v-if="loading">
        <h1>PAGE LOADING</h1>
    </div>
    <div v-if="!loading">
      <div
        class="tournamentCard card text-center text-white bg-warning mb-3"
        v-for="tournament in tournamentList"
        :key="tournament.tournamentId"
      >
        <h3>Name: {{ tournament.name }}</h3>
        <p> Game Type: {{tournament.game_type}} </p>
        <p>Date: {{tournament.date}}</p>
        <p>Location: {{tournament.location}}</p>
        <p>Fee: {{tournament.fee}} </p>
        <p>Description: {{tournament.description}} </p>
        <p>Prize: {{tournament.prize}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import TournamentServices from "../services/TournamentServices.js";
export default {
  data() {
    return {
      loading: true,
      tournamentList: [],
    };
  },

  async created() {

    TournamentServices.viewAllTournaments().then(response => {
      this.tournamentList = response.data;
      this.loading = false;
    })
      // this.tournamentList = this.$store.state.tournaments
    //   uncomment when backend is done
    // try {
    //   const response = await TournamentServices.viewAllTournaments();
    //   if (response.status !== 200) {
    //     console.log(response.statusText);
    //     return;
    //   }
    //   this.loading = false;
    // } catch (err) {
    //   console.log(err);
    // }
  },
};
</script>

<style scoped>
h1 {
  color: orange;
  text-align: center;
  margin: 20px 0px 50px 20px;
}

</style>
