<template>
  <div v-if="this.match" class="match-container" id="match-container">
    <h3 class="text-center">Match {{ this.$props.index + 1 }}</h3>
    <button
      v-if="isHost(this.$props.tournament)"
      @click="deleteM"
      class="btn del-btn"
    >
      <i class="bi bi-trash"></i>
    </button>
    <p v-if="this.errMessage" class="err-message">{{ this.errMessage }}</p>
    <form @submit.prevent="saveMatch">
      <div class="row">
        <div class="col-sm-6">
          <label>Team One</label>
          <select
            :disabled="!isHost(this.$props.tournament)"
            required
            class="form-control"
            v-model="match.team_one"
          >
            <option
              v-for="team in this.$props.tournament.invites"
              :key="team.team_id"
              :value="team.team_id"
            >
              {{ team.team_name }}
            </option>
          </select>
        </div>
        <div class="col-sm-6">
          <label>Team Two</label>
          <select
            :disabled="!isHost(this.$props.tournament)"
            required
            class="form-control"
            v-model="match.team_two"
          >
            <option
              v-for="team in this.$props.tournament.invites"
              :key="team.team_id"
              :value="team.team_id"
            >
              {{ team.team_name }}
            </option>
          </select>
        </div>
        <div class="col-sm-6">
          <label for="team-one-score">Team One Score</label>
          <input
            :readonly="!isHost(this.$props.tournament)"
            class="tournament-input form-control"
            type="number"
            id="team-one-score"
            v-model="match.team_one_wins"
            required
          />
        </div>
        <div class="col-sm-6">
          <label for="team-two-score">Team Two Score</label>
          <input
            :readonly="!isHost(this.$props.tournament)"
            class="tournament-input form-control"
            type="number"
            id="team-two-score"
            v-model="match.team_two_wins"
            required
          />
        </div>
        <div class="col-sm-12">
          <button
            v-if="isHost(this.$props.tournament)"
            class="btn"
            type="submit"
          >
            {{ this.match.isLocal ? "Save" : "Update" }}
          </button>
          <p v-if="this.success" class="success-msg">{{ this.success }}</p>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import TournamentServices from "../services/TournamentServices";
import { isHost } from "../util/util";
export default {
  props: {
    originalMatch: Object,
    tournament: Object,
    index: Number,
    deleteMatch: Function,
  },
  data() {
    return {
      match: null,
      errMessage: null,
      success: null,
    };
  },
  created() {
    this.match = this.$props.originalMatch;
  },
  methods: {
    isHost,
    deleteM() {
      this.$props.deleteMatch(this.match.pairing_id);
    },
    async saveMatch() {
      try {
        if (this.match.team_one === this.match.team_two) {
          this.errMessage = "Teams cannot be the same";
          return;
        }

        if (!this.match.isLocal) {
          TournamentServices.updatePairings(
            this.match.pairing_id,
            this.match
          ).then((response) => {
            if (response.status !== 200) {
              return;
              
            }
            this.$store.state.matches[this.$props.index] = this.match;
            this.success = "Updated Successfully"
          });
          return;
        }
        this.match.pairing_id = undefined;
        this.match.isLocal = undefined;
        const response = await TournamentServices.addPairing(this.match);
        if (response.status !== 200) {
          throw new Error("Failed creating match: " + response.data);
        }
        this.match = response.data;
        this.$store.state.matches[this.$props.index] = response.data;
      } catch (err) {
        console.log(err);
      }
    },
    
  },
};
</script>

<style scoped>
.match-container {
  position: relative;
  padding: 1em;
  color: white;
}
.err-message {
  color: red;
}
.success-msg {
  color: green;
  margin-top: 5px;
}
.del-btn {
  position: absolute;
  top: 5px;
  right: 5px;
  background-color: transparent;
  color: red;
}
</style>
