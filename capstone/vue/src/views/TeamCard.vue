<template>
<div class="card">
  <team-card v-bind:teamID="parseInt($route.params.id)"/>
</div>
</template>

<script>
import TeamCard from '../components/TeamCard.vue'
import TournamentServices from '../services/TournamentServices'
export default {
  name: 'Team-Card-View',
  components: { TeamCard },
  props: {
    teamID: Number,
  },
  data(){
    return {
      team: {
        team_id: 0,
        team_name: "",
        isAcceptingMembers: false,
        team_captain: "",
        team_description: "",
      },
    };
  },
  created(){
    const teamID = this.$route.params.teamID
    TournamentServices.getTeamById(teamID).then((response) => {
      this.team = response.data;
    });
  },
};
</script>

<style>

</style>