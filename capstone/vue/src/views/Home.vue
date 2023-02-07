<template>
  <div class="home">
    <h1>Home</h1>
    <browse-tournaments/>
    <hr class="solid"/>
    <user-teams/>
    <hr class="solid"/>
    <teams-list/>
  </div>
</template>

<script>
import BrowseTournaments from '../components/BrowseTournaments.vue';
import TeamsList from '../components/TeamsList.vue';
import UserTeams from '../components/UserTeams.vue';

export default {
  components: {  BrowseTournaments, TeamsList, UserTeams },
  name: "home",
 
};
</script>

<style scoped>
.home {
  color: white;
}
.solid {
  margin: 100px auto;
  border-top: 3px solid #ff7300;
}
</style>