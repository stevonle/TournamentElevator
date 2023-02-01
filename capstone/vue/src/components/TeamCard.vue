<template>
  <div class="card text-center text-white bg-warning mb-3" style="max-width: 15rem;">
      <div class="card-body">
    <h2 class="team-name">{{ this.team.team_name }}</h2>
    <p class="team-description">{{ this.team.team_description }}</p>
        </div>
  </div>
</template>

<script>
export default {
    name: "team-card",
    props: {
        team: Object,
    }
}
</script>

<style>

/* .card {
  border: 2px solid orange;
  border-radius: 10px;
  width: 200px;
  height: 150px;
  margin: 20px;
} */


/* .card .team-name {
  font-size: 1.5rem;
}

.card .team-description {
  font-size: 1rem;
} */

</style>