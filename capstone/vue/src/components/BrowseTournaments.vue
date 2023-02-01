<template>
  <div>
    <h1>All Tournaments</h1>
    <div v-if="loading">
        <h1>PAGE LOADING</h1>
    </div>
    <div v-if="!loading">
      <div
        class="tournamentCard"
        v-for="tournament in tournamentList"
        :key="tournament.tournamentId"
      >
        <h3>Name: {{ tournament.name }}</h3>
      </div>
    </div>
  </div>
</template>

<script>
// import TournamentServices from "../services/TournamentServices.js";
export default {
  data() {
    return {
      loading: false,
      tournamentList: [],
    };
  },

  async created() {
      this.tournamentList = this.$store.state.tournaments
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

<style></style>
