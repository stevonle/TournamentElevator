// consulted http://jsfiddle.net/4KZ6T/236/
<template>
  <section class="bracket-container" v-if="matches.length > 0">
    <div v-for="(round, idx) in numOfRounds" :key="idx" class="bracket-container container">
      <h4 class="round-title">Round: {{ idx + 1 }}</h4>
      <table class="bracket">
        <!-- <tr>
          <th>Team One</th>
          <th>Team Two</th>
        </tr> -->
        <hr class="solid"/>
        <div v-for="match in getMatchesByRound(round)" :key="match.pairing_id" class="match">
          
          <tr>
            <td><p> {{ getTeamName(match.team_one) }} {{match.team_one_wins}}</p></td>
           
          </tr>
          <tr>
            <td><p> {{ getTeamName(match.team_two)}} {{match.team_two_wins}}</p></td>
            <hr class="solid"/>
          </tr>
        </div>
      </table>
    </div>
    <!-- <table
      summary="Tournament Bracket"
      class="bracket"
      v-for="(round, idx) in numOfRounds + 1"
      :key="idx"
    >
      <tr>
        <th>
          Round {{ idx }} <br />
          {{ $props.tournament.date }}
        </th>
      </tr>
      <tr v-for="match in matches" :key="match.pairing_id">
        <td>
          <p>{{ getTeamName(match.team_one) }}</p>
        </td>
        <td>
          <p>{{ getTeamName(match.team_two) }}</p>
        </td>
      </tr>
    </table> -->
  </section>
</template>

<script>
import TournamentServices from "../services/TournamentServices";
export default {
  data() {
    return {
      numOfRounds: 1,
      matches: [],
    };
  },
  props: {
    tournament: Object,
  },
  created() {
    TournamentServices.getPairingsForTournament(this.$props.tournament.id)
      .then((res) => {
        this.matches = res.data;
        this.numOfRounds = this.getNumberOfRound();
      })
      .catch((err) => console.log(err));
  },
  methods: {
    getNumberOfRound() {
      const numOfteams = this.tournament.invites.length;
      switch (true) {
        case numOfteams <= 2:
          return 1;
        case numOfteams <= 4:
          return 2;
        case numOfteams <= 8:
          return 3;
        case numOfteams <= 16:
          return 4;
      }
    },
    getMatchesByRound(round) {
      return this.matches.filter((m) => m.round === round);
    },
    getTeamName(teamID) {
      if (!this.$props.tournament || !this.$props.tournament.invites)
        return "UKNOWN";
      const team = this.$props.tournament.invites.find(
        (t) => t.team_id === teamID
      );
      if (!team) return "UKNOWN";
      return team.team_name;
    },
  },
};
</script>

<style scoped>
table.bracket {
  border-collapse: collapse;
  border: none;
  display: inline;
}

.bracket td {
  width: 20em;
  margin: 0;
  padding: 10px 0px 10px 0px;
  display: block;
}

.bracket td p {
  border-bottom: solid 1px white;
  border-top: solid 1px white;
  border-right: solid 1px white;
  margin: auto 5px;
  padding: 5px 5px 5px 5px;
  color: white;
}

.bracket th {
  text-align: center;
}

.bracket-container {
  background-color: #ff7300;
  width: 75%;
  height: 500px;
  margin: auto;
  display: grid;
  /* grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr 1fr; */
  grid-template-areas: "round-title round-title round-title round-title"
  "match match match match"
  "match match match match"
  "match match match match";
}
.container {
  /* display: flex;
  flex-direction: column;
  margin: auto;
   */
}
.match {
  grid-area: match;
  /* display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: space-around;
  margin: auto; */

  
}
.round-title {
  grid-area: round-title;
   /* display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: start;
  align-content: flex-start; */


}
.bracket-lead {
  margin-left: 100px;
  justify-content: right;
  
}
h4 {
  text-shadow: 2px 2px #676767;
  color: white;
}
.solid {
  border-top: 2px solid #676767;
  width: 100%;
  margin: auto;
}
</style>
