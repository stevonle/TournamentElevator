<template>
  <section>
    <div v-if="loading">Loading Members</div>
    <div v-if="!loading">
      <ul class="list-group">
        <li
          class="list-group-item list-group-item-warning"
          v-for="member in memberList"
          v-bind:key="member.id"
        >
          {{ member.username }}
        </li>
        <li
          v-show="isHost()"
          class="list-group-item list-group-item-warning"
          v-for="user in tentativeMembers"
          v-bind:key="user.id"
        >
          {{ user.username }}
          <span
            class="badge badge-success badge-pill"
            v-on:click.prevent="ApproveMember(this.userID, this.teamID)"
            >Approve</span
          ><span
            class="badge badge-danger badge-pill"
            v-on:click.prevent="RejectMember(this.userID, this.teamID)"
            >Reject</span
          >
        </li>
      </ul>
    </div>
  </section>
</template>

<script>
import TournamentServices from "../services/TournamentServices";
export default {
  name: "Team Members",
  data() {
    return {
      members: [],
      teamID: Number(this.$route.params.teamID),
      loading: true,
      currentTeam: {},
      tentativeMembers: [],
    };
  },
  created() {
    TournamentServices.getTeamById(this.teamID).then((response) => {
      this.currentTeam = response.data;
    });
    TournamentServices.getTeamMembers(this.teamID).then((response) => {
      this.members = response.data;
      this.loading = false;
    });
  },

  methods: {
    isHost() {
      return this.$store.state.user.id === this.currentTeam.team_captain;
    },
    ApproveMember(userID, teamID) {
      TournamentServices.addMemberToTeam(userID, teamID);
    },
    Rejectmember(userID, teamID) {
      TournamentServices.rejectMemberFromTeam(userID, teamID);
    },
  },
};
</script>

<style scoped>
</style>