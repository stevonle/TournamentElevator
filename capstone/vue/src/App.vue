<template>
  <div id="app">
    <div id="nav" class="navbar navbar-light bg-light">
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> -->
      <nav-bar/>
    </div>
    <router-view />
  </div>
</template>

<script>

import NavBar from '/src/components/NavBar.vue'

export default {
  name: 'App',
  components: {
    'nav-bar' : NavBar
  }
};
</script>
