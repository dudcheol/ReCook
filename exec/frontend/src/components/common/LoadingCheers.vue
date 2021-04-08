<template>
  <VueLottiePlayer
    name="loading"
    loop
    :height="height + 'px'"
    :width="width + 'px'"
    path="https://assets3.lottiefiles.com/packages/lf20_jUMNii.json"
  />
</template>
<script>
import VueLottiePlayer from 'vue-lottie-player';
export default {
  props: {
    height: Number,
    width: Number,
  },
  components: {
    VueLottiePlayer,
  },
};
</script>
<style lang=""></style>
