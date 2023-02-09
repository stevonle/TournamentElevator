<template>
  <div class="matches-container"> 
    <h2 class="text-center">Manage Tournament Matches</h2>
    <div class="mb-2 text-white filter-input">
      <label for="tournament-name">Current Round</label>
      <select v-model.number="currentRound" required class="form-control">
        <option v-for="index in numOfRounds" :key="index" :value="index">
          Round {{ index }}
        </option>
      </select>
    </div>
    <button
      v-if="isHost(this.$props.tournament)"
      @click="this.addMatch"
      class="btn add-btn"
    >
      <i class="bi bi-plus-circle"></i>
    </button>
    <div v-if="this.matches.length > 0">
      <div v-for="(match, index) in this.matches" :key="match.pairing_id">
        <MatchCard
          :originalMatch="match"
          :index="index"
          :tournament="tournament"
          :deleteMatch="deleteMatch"
        />
      </div>
    </div>
  </div>
</template>

<script>
import MatchCard from "../components/MatchCard.vue";
import TournamentServices from "../services/TournamentServices";
import { isHost } from "../util/util";
export default {
  props: {
    tournament: Object,
  },
  components: {
    MatchCard,
  },
  data() {
    return {
      currentRound: 1,
      numOfRounds: 1,
      matches: [],
    };
  },
  watch: {
    currentRound() {
      this.filteredMatches();
    },
  },
  created() {
    TournamentServices.getPairingsForTournament(this.$props.tournament.id)
      .then((res) => {
        this.$store.state.matches = res.data;
        this.numOfRounds = this.getNumberOfRound();
        this.filteredMatches();
      })
      .catch((err) => console.log(err));
  },
  methods: {
    isHost,
    addMatch() {
      //Adds a local match
      this.$store.state.matches.push({
        pairing_id: Math.random().toString(),
        tournament_id: this.tournament.id,
        team_one: null,
        team_two: null,
        round: this.currentRound,
        isLocal: true,
        team_one_wins: 0,
        team_two_wins: 0,
      });
      this.filteredMatches();
    },
    filteredMatches() {
      this.matches = this.$store.state.matches.filter(
        (m) => m.round === this.currentRound
      );
    },
    deleteMatch(id) {
      const match = this.$store.state.matches.find((m) => m.pairing_id === id);
      if (match.isLocal) {
        this.$store.state.matches = this.$store.state.matches.filter(
          (m) => m.pairing_id !== id
        );
        this.filteredMatches();
        return;
      }
      TournamentServices.deletePairing(match.pairing_id)
        .then((res) => {
          if (res.status !== 200) {
            return;
          }
          //Remove it locally. after server returns 200.
          this.$store.state.matches = this.$store.state.matches.filter(
            (m) => m.pairing_id !== id
          );
          this.filteredMatches();
        })
        .catch((err) => console.log(err));
    },
    getNumberOfRound() {
      const numOfteams = this.$props.tournament.invites.length;
      switch (true) {
        case numOfteams <= 2:
          return 1;
        case numOfteams <= 4:
          return 2;
        case numOfteams <= 8:
          return 3;
        case numOfteams <= 16:
          return 4;
      }
    },
  },
};
</script>

<style scoped>
.matches-container {
  color: white;
  width: 50%;
  margin: 15px auto;
  padding: 1em;
  position: relative;
  background-color: #ff7300;
  border-radius: 3px;
}
.add-btn {
  position: absolute;
  background-color: transparent;
  color: green;
  top: 5px;
  right: 5px;
}
</style>
