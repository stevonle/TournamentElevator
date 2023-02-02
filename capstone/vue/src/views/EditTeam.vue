<template>
  <div>
      <h1> Edit Team </h1>
      <new-team-form v-bind:teamID="parseInt($route.params.teamID)"/>
      </div>
</template>

<script>
import NewTeamForm from "../components/NewTeamForm.vue"

export default {
  components: { NewTeamForm },
}
</script>

<style>

</style>