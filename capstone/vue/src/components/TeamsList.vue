<template>
  <section>
    <h1>Viewing all teams</h1>
    <div v-if="loading">
      <h1>PAGE LOADING</h1>
    </div>
    <div v-if="!loading" class="container card-group">
      <div class="row">
        <div
          class="col-md-6 col-lg-4 card-group"
          v-for="team in teamsList"
          v-bind:team="team"
          v-bind:key="team.team_id"
        >
          <div
            @click="viewTeamDetails(team.team_id)"
            class="card team-card text-center text-white"
          >
            <h2 class="team-name">Team Name: {{ team.team_name }}</h2>
            <textarea
              :value="team.team_description"
              class="form-control description-container"
              type="text"
              readonly
              rows="3"
            />
            
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import TournamentServices from "../services/TournamentServices";
export default {
  data() {
    return {
      teamsList: [],
      loading: true,
      
    };
  },
  created() {
    TournamentServices.viewAllTeams().then((response) => {
      this.teamsList = response.data;
      this.loading = false;
      console.log(response.data);
    });
  },
  methods: {
    viewTeamDetails(teamID){
      this.$router.push(`/teams/${teamID}`)
    },
      
    },
};
</script>

<style scoped>
.team-card {
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