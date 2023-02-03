<template>
<div>
  <team-card/>
  <team-members v-bind:teamID="parseInt($route.params.teamID)"/>
</div>
</template>

<script>
import TeamCard from '../components/TeamCard.vue'
import TeamMembers from '../components/TeamMembers.vue';
export default {
  name: 'Team-Card-View',
  components: { TeamCard, TeamMembers },
};

</script>

<style>

</style>