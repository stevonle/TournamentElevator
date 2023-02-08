// consulted http://jsfiddle.net/4KZ6T/236/
<template>
  <section>
    <table
      summary="Tournament Bracket"
      class="bracket"
      v-for="(round, idx) in numOfRounds"
      :key="idx"
    >
      <tr>
        <th>
          Round {{ idx }} <br />
          {{ this.tournament.date }}
        </th>
      </tr>
      <tr v-for="pairing in this.pairings" :key="pairing.id">
        <td>
          <p>{{ getTeamName(pairing.team_one) }}</p>
        </td>
        <td rowspan="2"><p></p></td>
        <td rowspan="4"><p></p></td>
      </tr>
      <tr>
        <td>
          <p>{{ getTeamName(pairing.team_two) }}</p>
        </td>
      </tr>
    </table>
  </section>
</template>

<script>
import TournamentServices from "../services/TournamentServices";
export default {
  data() {
    return {
      pairings: [],
      tournamentID: Number(this.$route.params.id),
      numOfRounds: 1,
      tournament: {},
      filteredPairs: [],
      currentRound: 1,
    };
  },
  created() {
    TournamentServices.getPairingsForTournament(this.tournamentID)
      .then((res) => {
        this.pairings = res.data;
        this.numOfRounds = this.getNumberOfRound();
      })
      .catch((err) => console.log(err));
    TournamentServices.getTournamentById(this.tournamentID).then((res) => {
      this.tournament = res.data;
    });
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
    getTeamName(teamID) {
      TournamentServices.getTeamById(teamID).then((res) => {
        return res.data.team_name;
      });
    },
    filteredPairings() {
      this.filteredPairs = this.pairings;
      this.filteredPairs = this.pairings.filter((pairing) => {
        return pairing.round === this.idx;
      });
    },
  },
};
</script>

<style scoped>
table.bracket {
  border-collapse: collapse;
  border: none;
}

.bracket td {
  vertical-align: middle;
  width: 40em;
  margin: 0;
  padding: 10px 0px 10px 0px;
}

.bracket td p {
  border-bottom: solid 1px black;
  border-top: solid 1px black;
  border-right: solid 1px black;
  margin: 0;
  padding: 5px 5px 5px 5px;
}

.bracket th {
  text-align: center;
}
</style>