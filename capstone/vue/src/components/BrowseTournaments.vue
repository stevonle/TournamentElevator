<template>
  <div>
    <h1>All Tournaments</h1>
    <div v-if="loading">
      <Loading />
    </div>
    <div v-if="!loading" class="container">
      <div
        class="mb-2 text-white filter-input"
        v-if="this.$route.name !== 'home'"
      >
        <label for="tournament-name">Filter Tournaments</label>

        <select @change="filteredTournament" required class="form-control">
          <option value="all">All</option>
          <option :selected="this.$route.query.filter" value="myTournaments">
            My Tournaments
          </option>
        </select>
      </div>

      <div class="row">
        <div
          class="col-md-6 col-lg-4"
          v-for="tournament in tournamentList"
          :key="tournament.id"
        >
          <div
            @click="viewTournamentDetails(tournament.id)"
            class="tournament-card card text-center text-white"
          >
            <h2>{{ tournament.name }}</h2>
            <div><img :src="getGameIcon(tournament.gametype)" /></div>

            <p>Game Type: {{ getGameName(tournament.gametype) }}</p>
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
import Loading from "../components/Loading.vue";
import { getGameName } from "../util/util.js";
import { getGameIcon } from "../util/util.js";
export default {
  data() {
    return {
      loading: true,
      tournamentList: [],
      isHome: false,
    };
  },
  components: {
    Loading,
  },
  async created() {
    console.log(this.$route);
    try {
      const response = await TournamentServices.viewAllTournaments();
      if (response.status !== 200) {
        return;
      }

      this.$store.state.tournaments = response.data;
      if (this.$route.query.filter === "myTournaments") {
        this.filteredTournament({ target: { value: "myTournaments" } });
      } else {
        this.tournamentList = response.data;
      }
      this.loading = false;
    } catch (err) {
      console.log(err);
    }
  },

  methods: {
    getGameName,
    getGameIcon,

    viewTournamentDetails(tournamentId) {
      this.$router.push(`/tournament/${tournamentId}`);
    },
    filteredTournament(e) {
      const tempUser = this.$store.state.user;
      let filteredTournament = [];
      const type = e.target.value;
      switch (type) {
        case "all":
          this.tournamentList = this.$store.state.tournaments;
          break;
        case "myTournaments":
          filteredTournament = this.$store.state.tournaments.filter((t) => {
            return t.host == tempUser.id;
          });
          this.tournamentList = filteredTournament;
          break;
      }
    },
  },
};
</script>

<style scoped>
.filter-input {
  width: 200px;
}
.tournament-card {
  background-color: #ff7300;
  width: 100%;
  max-height: 400px;
  cursor: pointer;
  margin-top: 30px;
}

h4 {
  text-shadow: 2px 2px #676767;
}

.description-container {
  background-color: #ff7300;
  color: white;
  border: none;
  text-align: center;
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
