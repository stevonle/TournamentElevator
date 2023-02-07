<template>
<section>
  <div>
      <new-team-form v-bind:teamID="parseInt($route.params.teamID)"/>
      </div>
      <div class='filler'>
        </div>
        </section>
</template>

<script>
import NewTeamForm from "../components/NewTeamForm.vue"

export default {
  components: { NewTeamForm },
}
</script>

<style>

</style>