<template>
  <div>
    <div
      v-if="this.tournament"
      class="card text-black bg-warning card-container"
    >
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
                :disabled="!isHost()"
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
            <div class="col-sm-6" id="tournament-completed" v-show="isHost()">
              <label for="tournament-completed">Tournament Complete</label>
              <input
                class="tournament-input form-control"
                type="checkbox"
                id="tournament-completed"
                v-model="tournament.completed"
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
    <TournamentBracket v-if="this.tournament" :tournament="this.tournament"/>
    <TournamentMatches v-if="this.tournament && isHost()" :tournament="this.tournament" />
    
    <div v-if="isHost()" class="invites-container">
      <table class="table table-bordered table-dark">
        <thead>
          <tr>
            <th scope="col">Team Name</th>
            <th scope="col">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="invite in tournament.invites" :key="invite.team_name">
            <td>{{ invite.team_name }}</td>
            <td>
              <div v-if="!invite.accepted">
                <button
                  class="btn"
                  @click="acceptTeam(invite.tournament_id, invite.team_id)"
                >
                  Accept
                </button>
                &nbsp;

                <button
                  class="btn"
                  @click="rejectTeam(invite.tournament_id, invite.team_id)"
                >
                  Reject
                </button>
              </div>
              <div v-if="invite.accepted">
                <h5>Accepted</h5>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import TournamentServices from "../services/TournamentServices.js";
import TournamentMatches from "../components/TournamentMatches.vue";
import TournamentBracket from '../components/TournamentBracket.vue';
export default {
  data() {
    return {
      tournament: null,
      isReadOnly: false,
      winning_team: "",
    };
  },
  components: {
    TournamentMatches,
    TournamentBracket,
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
      if (!window.confirm("Are you sure you want to make changes?")) return;
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
      if (!window.confirm("Are you sure you want to delete?")) return;
      TournamentServices.deleteTournament(this.tournament.id).then(
        (response) => {
          if (response.status !== 200) {
            return;
          }
          this.$router.push("/tournament/all");
        }
      );
    },
    isHost() {
      if (!this.tournament) return false;
      return this.$store.state.user.id === this.tournament.host;
    },
    acceptTeam(tournamentId, teamId) {
      TournamentServices.acceptTeamForTournament(tournamentId, teamId).then(
        (response) => {
          if (response.status !== 200) {
            return;
          }

          window.location.reload();
        }
      );
    },
    rejectTeam(tournamentId, teamId) {
      TournamentServices.rejectTeamForTournament(tournamentId, teamId).then(
        (response) => {
          if (response.status !== 200) {
            return;
          }
          window.location.reload();
        }
      );
    },
    setReadOnly() {
      if (this.isReadOnly === true) {
        this.isReadOnly = false;
      } else {
        this.isReadOnly = true;
      }
    },
    setWinningTeam(teamName) {
      this.winning_team = teamName;
    },
  },
};
</script>

<style scoped>
#tournament-completed {
  display: inline;
}
.invites-container {
  color: #ff7300;
  width: 50%;
  margin: 20px auto
}
.card-container {
  width: 500px;
  margin: 25px auto;
  color: white;
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

h3 {
    text-shadow: 2px 2px #676767;
}
</style>
