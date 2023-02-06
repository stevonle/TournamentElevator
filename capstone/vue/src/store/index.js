import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem("token");
const currentUser = JSON.parse(localStorage.getItem("user"));

if (currentToken != null) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || "",
    user: currentUser || {},
    teams: [],
    matches: [],
    tournaments: [
      // {
      //   tournamentId: 1,
      //   name: "Super Mario",
      //   gametype: 5,
      //   date: "03/03/2023",
      //   location: "Canada",
      //   fee: 100,
      //   description: "Big Tournament",
      //   prize: 100000,
      // },
      // {
      //   tournamentId: 2,
      //   name: "Super Liam",
      //   gametype: 5,
      //   date: "03/03/2023",
      //   location: "Canada",
      //   fee: 100,
      //   description: "Big Tournament",
      //   prize: 1004124000,
      // },
      // {
      //   tournamentId: 3,
      //   name: "Super Jarred",
      //   gametype: 5,
      //   date: "03/03/2023",
      //   location: "Tim Hortons",
      //   fee: 100,
      //   description: "Big Tournament",
      //   prize: "Car",
      // },
    ],
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem("user", JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      state.token = "";
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SAVE_TEAM(state, team) {
      state.teams.push(team);
    },
    SAVE_TOURNAMENT(state, tournament) {
      state.tournaments.push(tournament);
    },
  },
});
