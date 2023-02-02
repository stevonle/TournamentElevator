<template>
  <div
    v-if="this.tournament"
    class="card text-center text-white bg-warning mb-3"
    style="max-width: 15rem"
  >
    <div class="card-body">
      <h2 class="tournament-name">{{ this.tournament.name }}</h2>
      <p class="tournament-description">{{ this.tournament.description }}</p>
    </div>
  </div>
</template>

<script>
import TournamentServices from "../services/TournamentServices.js";
export default {
  data() {
    return {
      tournament: null,
    };
  },
  created() {
    TournamentServices.getTournamentById(this.$route.params.id).then(
      (response) => {
        if (response.status !== 200) {
          console.log(response.data);
          return;
        }
        this.tournament = response.data;
      }
    );
  },
};
</script>
