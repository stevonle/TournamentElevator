<template>
  <section>
    <div v-if="loading">
      <Loading />
    </div>
    <h1>Some Teams You Might Like</h1>
    
      <div
        v-if="!loading"
        id="demo"
        class="carousel slide"
        data-ride="carousel"
      >
        <!-- Indicators -->
        <ul class="carousel-indicators">
          <li data-target="#demo" data-slide-to="0" class="active"></li>
          <li data-target="#demo" data-slide-to="1"></li>
          <li data-target="#demo" data-slide-to="2"></li>
          <li data-target="#demo" data-slide-to="3"></li>
        </ul>

        <div class="carousel-inner center-block d-flex justify-content-center">
          <div
            class="carousel-item col-md-6 col-lg-4"
            v-for="(team, idx) in teamsList"
            v-bind:team="team"
            v-bind:key="team.team_id"
            :class="{ active: idx == 0 }"
          >
            <div
              @click="viewTeamDetails(team.team_id)"
              class="team-card text-center text-white"
            >
              <h2 class="team-name">{{ team.team_name }}</h2>
              <textarea
                :value="team.team_description"
                class="description-container"
                type="text"
                readonly
              />
            </div>
          </div>
        </div>
      

      <!-- Left and right controls -->
      <a class="carousel-control-prev" href="#demo" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
      </a>
      <a class="carousel-control-next" href="#demo" data-slide="next">
        <span class="carousel-control-next-icon"></span>
      </a>
    </div>
    <br/>
    <br/>
  </section>
</template>

<script>
import TournamentServices from "../services/TournamentServices";
import Loading from "../components/Loading.vue";
export default {
  data() {
    return {
      teamsList: [],
      loading: true,
    };
  },
  components: {
    Loading,
  },
  created() {
    TournamentServices.viewAllTeams().then((response) => {
      for (let i = 0; i < 5; i++) {
        this.teamsList.push(response.data[i]);
      }

      this.loading = false;
      console.log(response.data);
    });
  },
  methods: {
    viewTeamDetails(teamID) {
      this.$router.push(`/teams/${teamID}`);
    },
  },
};
</script>

<style scoped>
.team-card {
  background-color: #ff7300;
  width: 100%;
  max-height: 400px;
  cursor: pointer;
  padding: 10px;
  margin: 10px 0px;
}
h1 {
  color: #ff7300;
  text-align: center;
  margin: 20px 0px 50px 20px;
}

.description-container {
  background-color: #ff7300;
  color: white;
  border: none;
  text-align: center;
}

::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.5);
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.5);
}


/* 
.carousel-container {
    background-color: #ff7300;
  
  cursor: pointer;
  margin: auto;
  width: 50%;
 
}
.carousel-inner {
    width: 100%;
  
    margin: auto 0px;
}
.carousel-item {
  background-color: #ff7300;
   
  
  width: 100%;
  cursor: pointer;
 
}
.team-card {
  background-color: #ff7300;
  width: 100%;
  max-height: 400px;
  cursor: pointer;
  
} */
</style>