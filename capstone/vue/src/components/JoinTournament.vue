<template>
  <div class="tournament-container">
    <h2 class="text-white">Available Tournaments</h2>
    
    <select v-model="selectedTournamentId" class="form-control drop-down">
      <option
        v-for="tournament in this.tournaments"
        :value="tournament.id"
        :key="tournament.id"
      >
        {{ tournament.name }}
      </option>
    </select>
    <button @click="joinTournament()" class="btn">Join</button>
  </div>
</template>

<script>
import TournamentServices from "../services/TournamentServices.js";

export default {
  name: "join-tournament",
  props: {
    team: Object,
    members: Array,
  },
  data() {
    return {
      selectedTournamentId: null,
      tournaments: [],
    };
  },
  async created() {
    try {
      const response = await TournamentServices.viewAllTournaments();
      if (response.status !== 200) {
        return;
      }

      this.tournaments = response.data.filter((t) => {
        return this.members.findIndex((m) => m.id === t.host) === -1;
      });
    } catch (err) {
      console.log(err);
    }
  },
  methods: {
    joinTournament() {
      if (!this.selectedTournamentId) return;
      const team = {
        team_id: this.$props.team.team_id,
      };
      TournamentServices.joinTournament(this.selectedTournamentId, team)
        .then((res) => {
          if (res.status !== 200) {
            console.log(res.data);
            return;
          }
          alert("Tournament Application Submitted");
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>

<style scoped>

.tournament-container {
  width: 400px;
  margin: 20px auto;

}


.btn {
  margin-top: 10px;  
}
</style>
