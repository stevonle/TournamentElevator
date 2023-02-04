<template>
  <div
    class="card-container text-center text-white bg-warning mb-3"
    style="max-width: 15rem"
  >
    <div class="card-body">
      <h2 class="team-name">{{ team.team_name }}</h2>
      <p class="team-description">{{ team.team_description }}</p>
      <button
        v-show="!registeredUser && team.isAcceptingMembers"
        class="btn btn-md btn-primary btn-block"
        type="submit"
        placeholder="RequestToJoin"
        @click="requestJoin(team.team_id)"
      >
        Request
      </button>
    </div>
  </div>
</template>

<script>
import TournamentServices from "../services/TournamentServices";
export default {
  name: "team-card",
  props: {
    team: Object,
  },
  created() {
    console.log(this.$props);
  },
  methods: {
    requestJoin(teamID) {
      TournamentServices.joinRequestTeam(teamID).then((response) => {
        console.log(response.data);
        if (response.status === 200) {
          this.$router.push(`/teams/all`);
        }
      });
    },
  },
  computed: {
    registeredUser() {
      return this.$store.state.token === "";
    },
  },
};
</script>

<style>
.card-container {
  width: 500px;
  margin: 25px auto;
  background-color: orange;
}
.card-body {
  background-color: orange;
}
/* .card .team-name {
  font-size: 1.5rem;
}

.card .team-description {
  font-size: 1rem;
} */
</style>
