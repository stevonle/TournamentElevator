<template>
  <section>
    <h3>Viewing all teams</h3>
    <div class="all-teams-list card-group">
      <div
        class="card text-center text-white bg-warning mb-3"
        style="max-width: 15rem"
        v-for="team in this.teamsList"
        v-bind:team="team"
        v-bind:key="team.team_id"
      >
        <div class="card-body">
          <router-link
            v-bind:to="{ name: 'Teamcard', params: { teamID: team.team_id } }"
          >
            <h2 class="team-name">{{ this.team.team_name }}</h2>
            <p class="team-description">{{ this.team.team_description }}</p>
          </router-link>
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
      loading: false,
      teamsList: [],
    };
  },
   created() {
    TournamentServices.viewAllTeams().then((response) => {
      this.teamsList = response.data;
      this.loading = false;
      console.log(response.data);
    });
  },
};
</script>

<style>
.all-teams-list {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
</style>