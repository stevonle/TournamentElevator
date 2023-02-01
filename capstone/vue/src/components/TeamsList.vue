<template>
  <section>
    <h3>Viewing all teams</h3>
    <div class="all-teams-list card-group">
    
       <team-card
        v-for="team in this.teamsList"
        v-bind:team="team"
        v-bind:key="team.team_id"
        v-on:click.prevent="viewTeamDetails(team.team_id)"
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
  TournamentServices.viewAllTeams().then(response => {
    this.teamsList = response.data;
    this.loading = false;
    this.$store.state.teams = response.data;
    console.log(response.data);
  });
  },
  methods: {
    viewTeamDetails(teamID) {
      this.$router.push(`/teams/${teamID}`)
    }
  }
};
</script>

<style>
.all-teams-list {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
</style>