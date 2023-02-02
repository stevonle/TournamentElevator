<template>
  <div id="app">
      <nav-bar/>
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
