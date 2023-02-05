<template>
  <section>
    <h1>Viewing my tournaments</h1>
    <div v-if="loading">
      <Loading />
    </div>
    <div v-if="!loading" class="container">
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
            <h4>{{ tournament.name }}</h4>
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
            <router-link
              v-bind:to="{
                name: 'EditTournament',
                params: { id: tournament.id },
              }"
            >
              Edit Tournament
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import TournamentServices from "../services/TournamentServices";
import Loading from "../components/Loading.vue";
import { getGameName } from "../util/util.js";
export default {
  name: "UserTournaments",
  data() {
    return {
      tournamentList: [],
      loading: true,
    };
  },
  components: {
    Loading,
  },
  methods: {
    getGameName,
    //     getTeamData() {
    //       try {
    //         const response = TournamentServices.viewAllTeams();
    //         if (response.status !== 200) {
    //           console.log(response.statusText);
    //           return;
    //         }
    //         this.teamsList = response.data;
    //         this.loading = false;
    //       } catch (err) {
    //         console.log(err);
    //       }
    //     },
  },
  //   created() {
  //     this.getTeamData();
  //   },
  created() {
    TournamentServices.viewAllTournaments().then((response) => {
      this.tournamentList = response.data;
      this.loading = false;
      console.log(response.data);
    });
  },

  computed: {
    filteredTournament() {
      const tempUser = this.$store.state.user;
      let filteredTournament = this.tournamentList;
      filteredTournament = filteredTournament.filter((t) => {
        return t.host == tempUser.id;
      });
      console.log(filteredTournament);
      return filteredTournament;
    },
  },
  //   methods: {
  //     viewTournamentDetails(id){
  //       this.$router.push(`/tournament/${id}`)
  //     }
  //   }
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

.description-container {
  background-color: orange;
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
