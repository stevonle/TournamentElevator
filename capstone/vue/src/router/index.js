import Vue from "vue";
import Router from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Logout from "../views/Logout.vue";
import Register from "../views/Register.vue";
import store from "../store/index";
import NewTeamForm from "../components/NewTeamForm.vue";
import Tournament from "../views/Tournament.vue";
import BrowseTournaments from "../components/BrowseTournaments.vue";
import TeamsList from "../components/TeamsList.vue";
import TeamCard from "../views/TeamCard.vue";
import TournamentCard from "../components/TournamentCard.vue"

Vue.use(Router);

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/teams/create",
      name: "team-create",
      component: NewTeamForm,
      meta: {
        requiresAuth: true,
        title: "Team Creation",
      },
    },
    {
      path: "/tournament/create",
      name: "Tournament",
      component: Tournament,
      meta: {
        requiresAuth: true,
        title: "Tournament Registration",
      },
    },
    {
      path: "/tournament/all",
      name: "BrowseTournaments",
      component: BrowseTournaments,
      meta: {
        requiresAuth: false,
        title: "Tournaments",
      },
    },
    {
      path: "/teams/all",
      name: "BrowseTeams",
      component: TeamsList,
      meta: {
        requiresAuth: false,
        title: "Teams",
      },
    },
    {
      path: "/teams/:teamID",
      name: "Teamcard",
      component: TeamCard,
      meta: {
        requiresAuth: false,
        title: "Team Information",
      },
    },
    {
      path: "/tournament/:id",
      name: "TournamentCard",
      component: TournamentCard,
      meta: {
        requiresAuth: false,
        title: "Tournament Details",
      },
    },
  ],
});

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some((x) => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === "") {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

router.afterEach((to) => {
  Vue.nextTick(() => {
    document.title = to.meta.title || "Tournament Capstone";
  });
});

export default router;
