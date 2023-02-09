<template>
  <div>
    <h1 class="welcome">Welcome To Tournament Elevator</h1>
    <div class="phrase">
      <h4>Everyone is afraid of winning</h4>
      <h4>A Champion is afraid of losing</h4>
    </div>
    <img class="banner" src="../../public/img/banner.png" alt="banner.png" />
  </div>
</template>

<script>
export default {
  name: "banner",
};
</script>

<style>
.banner {
  width: 100%;
  max-height: 170px;
  margin-bottom: 280px;
}

.phrase {
  text-align: center;
  margin-bottom: 60px;
  text-shadow: 2px 2px #676767;
  font-family: "Permanent Marker", sans-serif;
}

.welcome {
  /* margin-top: 200px; */
}
</style>