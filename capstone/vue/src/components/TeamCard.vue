<template>
  <div
    class="card-container text-center text-white bg-warning mb-3"
    style="max-width: 15rem"
  >
    <div class="card-body">
      <h2 class="team-name">{{ team.team_name }}</h2>
      <p class="team-description">{{ team.team_description }}</p>
      <button
        v-show="!registeredUser && team.isAcceptingMembers && !isCaptain "
        class="btn btn-md btn-outline-light btn-block"
        type="submit"
        placeholder="RequestToJoin"
        @click="requestJoin(team.team_id)"
      >
        Request
      </button>
      <router-link v-show="isCaptain"
              v-bind:to="{ name: 'EditTeam', params: { teamID: team.team_id } }"
            >
             <button
        v-show="isCaptain"
        class="btn btn-md btn-outline-light btn-block"
        type="confirm"
      >
        Edit Team
      </button>
            </router-link>
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
    console.log(this.$props.team);
    console.log(this.$store.state.user.id)
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
    isCaptain() {
      return this.team.team_captain === this.$store.state.user.id
    }
  },
};
</script>

<style>
.card-container {
  width: 500px;
  margin: 25px auto;
  background-color: #ff7300;
}
.card-body {
  background-color: #ff7300;
}

.btn {
  margin-top: 2px;
}
/* .card .team-name {
  font-size: 1.5rem;
}

.card .team-description {
  font-size: 1rem;
} */
</style>
