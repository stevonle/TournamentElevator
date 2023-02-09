<template>
  <nav class="navbar fixed-top navbar-expand-lg navbar-dark">
    <img class="img-fluid nav-image" src="/img/SmallLogo.png" />
    <a class="navbar-brand" href="/">Tournament Elevator</a>
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      @click.stop="toggleNavbar()"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="/"
            >Home <span class="sr-only">(current)</span></a
          >
        </li>
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarDropdown"
            role="button"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            Teams
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="/teams/all">View Teams</a>
            <a class="dropdown-item" href="/teams/create">Create Team</a>
            <div class="dropdown-divider" v-show="!registeredUser"></div>
            <a
              class="dropdown-item"
              v-show="!registeredUser"
              href="/teams/myteams"
              >My Teams</a
            >
          </div>
        </li>
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarDropdown"
            role="button"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            Tournaments
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="/tournament/all">View Tournaments</a>
            <a class="dropdown-item" href="/tournament/create"
              >Create Tournaments</a
            >
            <div class="dropdown-divider" v-show="!registeredUser"></div>
            <a
              class="dropdown-item"
              v-show="!registeredUser"
              href="/tournament/all?filter=myTournaments"
              >My Tournaments</a
            >
          </div>
        </li>
      </ul>
      <div
        class="form-inline me-auto my-2 my-lg-0 justify-content-end"
        style="text-align: right"
      >
        <ul class="navbar-nav mr-auto">
          <li class="mr-2 idk">{{ this.$store.state.user.username }}</li>
          <li>
            <router-link
              v-bind:to="{ name: 'logout' }"
              v-if="$store.state.token != ''"
              >Logout</router-link
            >
          </li>
          <li class="mr-2">
            <router-link
              v-bind:to="{ name: 'login' }"
              v-if="$store.state.token == ''"
              >Login</router-link
            >
          </li>

          <li class="mr-2">
            <router-link
              v-bind:to="{ name: 'register' }"
              v-if="$store.state.token == ''"
            >
              Register</router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "NavBar",

  computed: {
    registeredUser() {
      return this.$store.state.token === "";
    },
  },
};
</script>

<style>
nav {
  background-color: rgba(67, 67, 67, 0.9);
}

.idk {
  color: white;
}
.nav-image {
  max-width: 60px;
}
</style>
