<template>
<div class="card">
  <team-card v-bind:teamID="parseInt($route.params.id)"/>
</div>
</template>

<script>
import TeamCard from '../components/TeamCard.vue'
export default {
  name: 'Team-Card-View',
  components: { TeamCard },
};

</script>

<style>

</style>