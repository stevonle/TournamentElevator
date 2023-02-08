<template>
<section>
  <div>
    <h1 v-show="isEditing">Create Team</h1>
    <h1 v-show="!isEditing">Edit Team</h1>
    <form class="new-team-form cardForm" v-on:submit.prevent="saveTeam">
      <label for="teamNameInput" class="form-label"> Team Name </label>
      <div class="mb-2">
        <input
          class="team-input"
          type="text"
          placeholder="Team Name"
          id="teamNameInput"
          v-model="team.team_name"
        />
      </div>

      <div class="mb-2">
        <label for="acceptingMembers" class="form-label acc">
          Accepting Members?
        </label>
        <input
          class="team-input inp"
          type="checkbox"
          placeholder="acceptingMembers"
          v-model="team.isAcceptingMembers"
          
        />
      </div>
      <div>
        <label for="teamDescription" class="form-label">
          Team Description
        </label>
        <textarea
          type="text"
          class="team-input"
          placeholder="Team Description"
          v-model="team.team_description"
        />
      </div>
      <br />
      <button
        v-show="isEditing"
        class="btn btn-md btn-outline-light btn-block"
        type="submit"
        placeholder="createTeamButton"
      >
        Create
      </button>
      <button
        v-show="!isEditing"
        class="btn btn-md btn-outline-light btn-block"
        type="submit"
        placeholder="createTeamButton"
      >
        Save Edit
      </button>
    </form>
      
  </div><div class='filler'>
        </div>
  </section>
</template>

<script>
import TournamentServices from "../services/TournamentServices.js";

export default {
  name: "new-team-form",
  props: {
    teamID: {
      type: Number,
      default: 0,
    },
  },
  computed: {
    isEditing() {
      return this.teamID === 0;
    },
  },
  data() {
    return {
      editing: false,
      team: {
        team_name: "",
        isAcceptingMembers: "",
        team_captain: "",
        team_description: "",
      },
    };
  },
  methods: {
    saveTeam() {
      const newTeam = {
        team_id: Number(this.$route.params.teamID),
        team_name: this.team.team_name,
        isAcceptingMembers: this.team.isAcceptingMembers,
        team_captain: this.team.team_captain,
        team_description: this.team.team_description,
      };
      //add section
      if (this.teamID === 0) {
        const tempUser = this.$store.state.user;
        newTeam.team_captain = tempUser.id;
        TournamentServices.addTeam(newTeam).then((response) => {
          if (response.status === 200) {
            this.$router.push(`/teams/all`);
          }
        });
      } else {
       
        
        //update section
        newTeam.team_id = this.teamID;
        newTeam.team_name = this.team.team_name;
        newTeam.isAcceptingMembers = this.team.isAcceptingMembers;
        newTeam.team_captain = this.team.team_captain;
        newTeam.team_description = this.team.team_description;
        TournamentServices.updateTeam(this.teamID, newTeam).then((response) => {
          if (response.status === 200) {
            this.$router.push(`/teams/all`);
          }
        });
      }
    },
  },

  created() {
    if (this.teamID != 0) {
      TournamentServices.getTeamById(this.teamID)
        .then((response) => {
          this.team = response.data;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert(
              "Team not available. You have entered an invalid team ID pathway."
            );
            this.$router.push("/");
          }
        });
    }
  },
};
</script>

<style scoped>
.new-team-form {
  color: #ff7300;
  margin: 35px auto;
  width: 100%;
  min-width: 300px;
  max-width: 400px;
  display: grid;
}

.team-input {
  display: block;
  width: 100%;
}

</style>
