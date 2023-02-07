<template>
  <div class="spinner1">
    <div class="bounce1" />
    <div class="bounce2" />
    <div class="bounce3" />
  </div>
</template>

<script>
export default {
  name: "Loading",
};
</script>

<style>
.spinner1 {
  margin: 3em auto;
  width: 12em;
  text-align: center;
}
.spinner1 > div {
  width: 45px;
  height: 32px;
  background-color: #ff7300;
  border-radius: 100%;
  display: inline-block;
  -webkit-animation: sk-bouncedelay 1.4s infinite ease-in-out both;
  animation: sk-bouncedelay 1.4s infinite ease-in-out both;
}
.spinner1 .bounce {
  -webkit-animation-delay: -0.32s;
  animation-delay: -0.32s;
}
.spinner1 .bounce2 {
  -webkit-animation-delay: -0.16s;
  animation-delay: -0.16s;
}

@-webkit-keyframes sk-bouncedelay {
  0%,
  80%,
  100% {
    -webkit-transform: scale(0);
  }
  40% {
    -webkit-transform: scale(1);
  }
}
@keyframes sk-bouncedelay {
  0%,
  80%,
  100% {
    -webkit-transform: scale(0);
    transform: scale(0);
  }
  40% {
    -webkit-transform: scale(1);
    transform: scale(1);
  }
}
</style>
