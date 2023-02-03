<template>
  <div v-if="this.tournament" class="card text-black bg-warning card-container">
    <div class="card-body">
      <button
      v-if="isHost()"
      class="btn delete-btn"
      @click="deleteTournament()"
      >
        <i class="bi bi-trash"></i>
      </button>
      <h3 class="card-title text-center">{{ tournament.name }}</h3>

      <form class="new-tournament-form" @submit.prevent="updateTournament">
        <div class="row">
          <div class="col-sm-6">
            <label for="tournament-name">Tournament Name</label>
            <input
              class="tournament-input form-control"
              type="text"
              id="tournament-name"
              v-model="tournament.name"
              :readonly="!isHost()"
              required
            />
          </div>
          <div class="col-sm-6">
            <label for="tournament-name">Tournament Game Type</label>

            <select
              :readonly="!isHost()"
              required
              class="form-control"
              v-model="tournament.gametype"
            >
              <option value="1">Football</option>
              <option value="2">Soccer</option>
              <option value="3">Basketball</option>
              <option value="4">Volleyball</option>
              <option value="5">Quidditch</option>
            </select>
          </div>
          <div class="col-sm-6">
            <label for="tournament-date">Tournament Date</label>
            <input
              :readonly="!isHost()"
              class="tournament-input form-control"
              type="date"
              id="tournament-date"
              v-model="tournament.date"
              required
            />
          </div>
          <div class="col-sm-6">
            <label for="tournament-location">Tournament Location</label>
            <input
              :readonly="!isHost()"
              class="tournament-input form-control"
              type="text"
              id="tournament-location"
              v-model="tournament.location"
              required
            />
          </div>
          <div class="col-sm-6">
            <label for="tournament-fee">Tournament Fee</label>
            <input
              :readonly="!isHost()"
              class="tournament-input form-control"
              type="number"
              id="tournament-fee"
              v-model="tournament.fee"
              required
            />
          </div>
          <div class="col-sm-6">
            <label for="tournament-prize">Tournament Prize</label>
            <input
              :readonly="!isHost()"
              class="tournament-input form-control"
              type="text"
              id="tournament-prize"
              v-model="tournament.prize"
              required
            />
          </div>
          <div class="col-sm-12">
            <label for="tournament-description">Tournament Description</label>
            <textarea
              :readonly="!isHost()"
              class="tournament-input form-control"
              type="text"
              id="tournament-description"
              v-model="tournament.description"
              required
            />
          </div>
          <div class="col-sm-12">
            <button v-if="isHost()" class="btn" type="submit">Update</button>
          </div>
        </div>
      </form>
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
  methods: {
    updateTournament() {
      TournamentServices.updateTournament(
        this.tournament.id,
        this.tournament
      ).then((response) => {
        if (response.status !== 200) {
          console.log(response.data);
          return;
        }
        window.alert("Changes Saved");
      });
    },
    deleteTournament() {
      if(!window.confirm("Are you sure you want to delete?")) return
      TournamentServices.deleteTournament(this.tournament.id).then(
        (response) => {
          if(response.status !== 200) {
            return;
          }
          this.$router.push('/tournament/all')
        }
      )
    },
    isHost() {
      return this.$store.state.user.id === this.tournament.host;
    },
  },
};
</script>

<style scoped>
.card-container {
  width: 500px;
  margin: 25px auto;
}

.delete-btn {
  position: absolute;
  top: 5px;
  right: 5px;
  border: none;
  background-color: transparent;
}
.delete-btn:hover {
  color: red;
}

::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.5);
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.5);
}
</style>
