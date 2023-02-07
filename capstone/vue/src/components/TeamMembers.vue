<template>
  <section>
    <div v-if="loading">
      <Loading />
    </div>
    <div v-if="!loading">
      <h2>Team Members</h2>
      <ul class="list-group">
        <li
          class="list-group-item list-group-item-warning"
          v-for="member in members"
          v-bind:key="member.id"
        >
          {{ member.username }}
          <span class="badge badge-primary" v-if="isCaptain(member.id)">
            Team Captain
          </span>
        </li>
        <li
          v-show="isHost()"
          class="list-group-item list-group-item-warning"
          v-for="user in tentativeMembers"
          v-bind:key="user.id"
        >
          {{ user.username }}
          <button class="btn accept-btn" v-on:click.prevent="ApproveMember(user.id)">
            <i class="bi bi-check"></i></button
          ><button class="btn delete-btn" v-on:click.prevent="RejectMember(user.id)">
            <i class="bi bi-x"></i>
          </button>
        </li>
      </ul>
    </div>
  </section>
</template>

<script>
import TournamentServices from "../services/TournamentServices";
import Loading from "../components/Loading.vue";
export default {
  name: "team-members",
  data() {
    return {
      members: [],
      teamID: Number(this.$route.params.teamID),
      loading: true,
      currentTeam: {},
      tentativeMembers: [],
    };
  },
  components: {
    Loading,
  },
  created() {
    this.getTeam(this.teamID);
    this.getMembers(this.teamID);
    this.getPendingMembers(this.teamID);
  },

  methods: {
    isHost() {
      return this.$store.state.user.id === this.currentTeam.team_captain;
    },
    isCaptain(userID) {
      return userID === this.currentTeam.team_captain;
    },
    ApproveMember(userID) {
      TournamentServices.addMemberToTeam(this.teamID, userID).then(
        (response) => {
          if (response.status === 200) {
            window.location.reload();
          }
        }
      );
    },
    RejectMember(userID) {
      TournamentServices.rejectMemberFromTeam(this.teamID, userID).then(
        (response) => {
          if (response.status === 200) {
            window.location.reload();
          }
        }
      );
    },
    getTeam(teamID) {
      TournamentServices.getTeamById(teamID).then((response) => {
        this.currentTeam = response.data;
      });
    },
    getMembers(teamID) {
      TournamentServices.getTeamMembers(teamID).then((response) => {
        this.members = response.data;
      });
    },
    getPendingMembers(teamID) {
      TournamentServices.getPendingMembers(teamID).then((response) => {
        this.tentativeMembers = response.data;
        this.loading = false;
      });
    },
  },
};
</script>

<style scoped>
.delete-btn {
  border: none;
  background-color: transparent;
}

.delete-btn:hover {
  color: red;
}
.accept-btn:hover {
  color: green;
}

.accept-btn {
  border: none;
  background-color: transparent;
}

.list-group {
  display: inline-block;
}
.list-group-item {
  background-color: #ff7300;
  color: white;
}
h2 {
  color: white;
}
</style>
