<template>
  <section>
    <h1>Viewing my teams</h1>
    <div v-if="loading">
      <Loading />
    </div>
    <div v-if="!loading" class="container card-group">
      <div class="row">
        <div
          class="col-md-6 col-lg-4 card-group"
          v-for="team in filteredTeams"
          v-bind:team="team"
          v-bind:key="team.team_id"
        >
          <div
            @click="viewTeamDetails(team.team_id)"
            class="card team-card text-center text-white"
          >
            <h2 class="team-name">{{ team.team_name }}</h2>
            <textarea
              :value="team.team_description"
              class="form-control description-container"
              type="text"
              readonly
              rows="3"
            />
            <router-link
              v-bind:to="{ name: 'EditTeam', params: { teamID: team.team_id } }"
            >
             <button
        
        class="btn btn-md btn-outline-light btn-block"
        type="confirm"
      >
        Edit Team
      </button>
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
export default {
  name: "UserTeams",
  data() {
    return {
      teamsList: [],
      loading: true,
    };
  },
  components: {
    Loading,
  },
  //   methods: {
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
  //   },
  //   created() {
  //     this.getTeamData();
  //   },
  created() {
    TournamentServices.viewAllTeams().then((response) => {
      this.teamsList = response.data;
      this.loading = false;
    });
  },

  computed: {
    filteredTeams() {
      const tempUser = this.$store.state.user;
      let filteredTeams = this.teamsList;
      filteredTeams = filteredTeams.filter((team) => {
        return team.team_captain == tempUser.id;
      });
      return filteredTeams;
    },
  },
  methods: {
    viewTeamDetails(teamID) {
      this.$router.push(`/teams/${teamID}`);
    },
  },
};
</script>

<style scoped>
.team-card {
 background-color: #ff7300;
  width: 1000px;
  max-height: 400px;
  cursor: pointer;
  padding: 10px;
  margin: 10px 0px;
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
