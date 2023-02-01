<template>
  <section>
    <h3>Viewing all teams</h3>
    <div class="all-teams-list card-group">
      <team-card
        v-for="team in teamsList"
        v-bind:team="team"
        v-bind:key="team.captainID"
      />
    </div>
  </section>
</template>

<script>
import TournamentServices from "../services/TournamentServices";
import TeamCard from "./TeamCard.vue";
export default {
  components: { TeamCard },
  data() {
    return {
      loading: false,
      teamsList: [],
    };
  },
  async created() {
    this.teamsList = this.$store.state.teams;
    try {
      const response = await TournamentServices.viewAllTeams();
      if (response.status !== 200) {
        console.log(response.statusText);
        return;
      }
      this.teamsList = response;
      this.loading = false;
    } catch (err) {
      console.log(err);
    }
    
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