<template>
  <div class="d-flex flex-column align-center justify-center">
    <VueLottiePlayer
      name="loading"
      loop
      :height="height + 'px'"
      :width="width + 'px'"
      path="https://assets4.lottiefiles.com/packages/lf20_v4d0iG.json"
    />
    <span class="grey--text text--darken-1">{{ text }}</span>
  </div>
</template>
<script>
import VueLottiePlayer from 'vue-lottie-player';
export default {
  props: {
    height: Number,
    width: Number,
    text: String,
  },
  components: {
    VueLottiePlayer,
  },
};
</script>
<style lang=""></style>
