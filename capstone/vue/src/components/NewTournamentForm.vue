<template>
  <div>
    <h1>Tournament Registration</h1>
    <form class="new-tournament-form" @submit.prevent="saveTournament">
      <div class="mb-2">
        <label for="tournament-name">Tournament Name</label>
        <input
          class="tournament-input form-control"
          type="text"
          id="tournament-name"
          v-model="tournament.name"
          required
        />
      </div>
      <div class="mb-2">
        <label for="tournament-name">Tournament Game Type</label>

        <select required class="form-control" v-model="tournament.gametype">
          <option value="1">Football</option>
          <option value="2">Soccer</option>
          <option value="3">Basketball</option>
          <option value="4">Volleyball</option>
          <option value="5">Quidditch</option>
        </select>
      </div>
      <div class="mb-2">
        <label for="tournament-date">Tournament Date</label>
        <input
          class="tournament-input form-control"
          type="date"
          id="tournament-date"
          v-model="tournament.date"
          required
        />
      </div>
      <div class="mb-2">
        <label for="tournament-location">Tournament Location</label>
        <input
          class="tournament-input form-control"
          type="text"
          id="tournament-location"
          v-model="tournament.location"
          required
        />
      </div>
      <div class="mb-2">
        <label for="tournament-fee">Tournament Fee</label>
        <input
          class="tournament-input form-control"
          type="number"
          id="tournament-fee"
          v-model="tournament.fee"
          required
        />
      </div>
      <div class="mb-2">
        <label for="tournament-description">Tournament Description</label>
        <textarea
          class="tournament-input form-control"
          type="text"
          id="tournament-description"
          v-model="tournament.description"
          required
        />
      </div>
      <div class="mb-2">
        <label for="tournament-prize">Tournament Prize</label>
        <input
          class="tournament-input form-control"
          type="text"
          id="tournament-prize"
          v-model="tournament.prize"
          required
        />
      </div>
      <br />

      <button
        class="btn btn-md btn-outline-light btn-block"
        type="submit"
        placeholder="createTeamButton"
      >
        Create
      </button>
    </form>
    <div class="filler"></div>
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
        if (response.status !== 201) {
          console.log(response.statusText);
          this.$store.commit("SAVE_TOURNAMENT", this.tournament);
          this.$router.push("/tournament/all");
          return;
        }

        
        this.$router.push("/tournament/all");
      } catch (err) {
        console.log(err);
      }
    },
  },
};
</script>

<style scoped>
.new-tournament-form {
  color: #ff7300;
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
</style>
