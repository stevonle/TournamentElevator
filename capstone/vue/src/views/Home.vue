<template>
  <div class="home">
    <browse-tournaments/>
    <hr class="solid"/>
    <user-teams/>
    <hr class="solid"/>
    <!-- <teams-list/> -->
    <team-carousel/>
  </div>
</template>

<script>
import BrowseTournaments from '../components/BrowseTournaments.vue';
// import TeamsList from '../components/TeamsList.vue';
import UserTeams from '../components/UserTeams.vue';
import TeamCarousel from '../components/TeamCarousel.vue';

export default {
  components: {  BrowseTournaments, UserTeams, TeamCarousel },
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