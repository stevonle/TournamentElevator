<template>
  <div>
    <h1>Tournament Registration</h1>
    <form class="new-tournament-form" @submit.prevent="saveTournament">
      <div class="mb-2">
        <label for="tournament-name">Tournament Name</label>
        <input
          class="tournament-input"
          type="text"
          id="tournament-name"
          v-model="tournament.name"
          required
        />
      </div>
      <div class="mb-2">
        <label for="tournament-date">Tournament Date</label>
        <input
          class="tournament-input"
          type="date"
          id="tournament-date"
          v-model="tournament.date"
          required
        />
      </div>
      <div class="mb-2">
        <label for="tournament-location">Tournament Location</label>
        <input
          class="tournament-input"
          type="text"
          id="tournament-location"
          v-model="tournament.location"
          required
        />
      </div>
      <div class="mb-2">
        <label for="tournament-fee">Tournament Fee</label>
        <input
          class="tournament-input"
          type="number"
          id="tournament-fee"
          v-model="tournament.fee"
          required
        />
      </div>
      <div class="mb-2">
        <label for="tournament-description">Tournament Description</label>
        <textarea
          class="tournament-input"
          type="text"
          id="tournament-description"
          v-model="tournament.description"
          required
        />
      </div>
      <div class="mb-2">
        <label for="tournament-prize">Tournament Prize</label>
        <input
          class="tournament-input"
          type="text"
          id="tournament-prize"
          v-model="tournament.prize"
          required
        />
      </div>
      <br />

      <button
        class="btn btn-md btn-primary btn-block"
        type="submit"
        placeholder="createTeamButton"
      >
        Create
      </button>
    </form>
  </div>
</template>

<script>
import TournamentServices from "../services/TournamentServices.js";

export default {
  name: "new-tournament-form",
  data() {
    return {
      tournament: {
        // name: "",
        // date: "",
        // location: "",
        // fee: 0,
        // description: "",
        // prize: 0,
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
        this.$router.push("/");
      } catch (err) {
        console.log(err);
      }
    },
  },
};
</script>

<style scoped>
.new-tournament-form {
  color: rgb(241, 179, 63);
  margin: 35px auto;
  width: 100%;
  min-width: 300px;
  max-width: 400px;
  display: grid;
}
.tournament-input {
  display: block;
  width: 100%;
}

h1 {
  color: orange;
  text-align: center;
  margin: 20px 0px 0px 0px;
}
</style>
