<template>
<section>
  <div v-if="this.team" class="team-info mb-2 text-center">
    <team-card :team="this.team" />
    <team-members v-bind:teamID="parseInt($route.params.teamID)" />
    <join-tournament
      v-if="this.team.team_captain === this.$store.state.user.id"
      :members="this.members"
      :team="this.team"
    />
  </div>
  <div class='filler'>
        </div>
</section>
</template>

<script>
import JoinTournament from "../components/JoinTournament.vue";
import TeamCard from "../components/TeamCard.vue";
import TeamMembers from "../components/TeamMembers.vue";
import TournamentServices from "../services/TournamentServices";

export default {
  name: "Team-Card-View",
  components: { TeamCard, TeamMembers, JoinTournament },
  data() {
    return {
      team: {
        team_id: 0,
        team_name: "",
        isAcceptingMembers: false,
        team_captain: "",
        team_description: "",
      },
      members: [],
    };
  },
  created() {
    const teamID = this.$route.params.teamID;
    TournamentServices.getTeamById(teamID).then((response) => {
      this.team = response.data;
    });
    TournamentServices.getTeamMembers(teamID).then((response) => {
      this.members = response.data;
    });
  },
};
</script>

<style scoped></style>
