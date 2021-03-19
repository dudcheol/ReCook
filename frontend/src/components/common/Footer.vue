<template>
  <v-bottom-navigation v-model="value" grow absolute flat>
    <v-btn class="bottom-btn" value="recent">
      <span>Home</span>
      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="favorites">
      <span>Feed</span>
      <v-icon>mdi-view-dashboard</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="nearby" x-large>
      <v-icon large>mdi-fridge-outline</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="nearby">
      <span>Watch</span>
      <v-icon>mdi-play-circle</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="nearby">
      <span>Mypage</span>
      <v-icon>mdi-account</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      value: 'recent',
    };
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped>
.v-btn.bottom-btn {
  min-width: 20px !important;
  font-size: 1px;
}
</style>
