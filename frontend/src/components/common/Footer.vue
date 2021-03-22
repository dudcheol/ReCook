<template>
  <v-bottom-navigation v-model="selectedView" grow absolute flat>
    <v-btn class="bottom-btn" value="Main">
      <span>Home</span>
      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="Feed">
      <span>Feed</span>
      <v-icon>mdi-view-dashboard</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="Fridge" x-large>
      <v-icon large>mdi-fridge-outline</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="Watch">
      <span>Watch</span>
      <v-icon>mdi-play-circle</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="Mypage">
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
      selectedView: 'Main',
    };
  },
  computed: {},
  watch: {
    selectedView: function(val) {
      this.$router.push({ name: val });
    },
  },
  methods: {},
};
</script>

<style scope>
.v-btn.bottom-btn {
  min-width: 20px !important;
  font-size: 1px;
}
</style>
