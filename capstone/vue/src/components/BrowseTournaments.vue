<template>
  <div>
    <h1>All Tournaments</h1>
    <div v-if="loading">
      <h1>PAGE LOADING</h1>
    </div>

    <div v-if="!loading" class="container">
      <div class="row">
        <div
          class="col-md-6 col-lg-4"
          v-for="tournament in tournamentList"
          :key="tournament.tournamentId"
        >
          <div @click="viewTournamentDetails(tournament.tournament_id)" class="tournament-card card text-center text-white">
            <h4>{{ tournament.name }}</h4>
            <p>Game Type: {{ getGameName(tournament.game_type) }}</p>
            <p>Date: {{ tournament.date }}</p>
            <p>Location: {{ tournament.location }}</p>
            <p>Fee: {{ tournament.fee }}</p>
            <p>Prize: {{ tournament.prize }}</p>
            <textarea
              :value="tournament.description"
              class="form-control description-container"
              type="text"
              readonly
              rows="3"
            />
          </div>
        </div>
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
    try {
      const response = await TournamentServices.viewAllTournaments();
      if (response.status !== 200) {
        console.log(response.statusText);
        return;
      }
      this.tournamentList = response.data;
      this.loading = false;
    } catch (err) {
      console.log(err);
    }
  },
  methods: {
    getGameName(type) {
      switch (type) {
        case 1:
          return "Football";
        case 2:
          return "Soccer";
        case 3:
          return "Basketball";
        case 4:
          return "Volleyball";
        case 5:
          return "Quidditch";
        default:
          return "Unknown";
      }
    },
    viewTournamentDetails(tournamentId) {
      this.$router.push(`/tournament/${tournamentId}`)
    }
  },
};
</script>

<style scoped>
.tournament-card {
  background-color: orange;
  width: 100%;
  max-height: 400px;
  cursor: pointer;
}
h1 {
  color: orange;
  text-align: center;
  margin: 20px 0px 50px 20px;
}

.description-container{
  background-color: orange;
  color: white;
  border: none;
  text-align: center;
}

::-webkit-scrollbar {
    width: 8px;
}
 
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
    box-shadow: 0 0 6px rgba(0,0,0,0.3);
    border-radius: 10px;
}
 
::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5); 
    box-shadow: 0 0 6px rgba(0,0,0,0.5);
}
</style>
