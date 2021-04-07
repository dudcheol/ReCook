<template>
  <v-bottom-navigation ref="nav" v-model="selectedView" grow fixed flat>
    <v-btn class="bottom-btn" value="Main">
      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="Feed">
      <v-icon>mdi-view-dashboard</v-icon>
    </v-btn>

    <v-btn
      class="bottom-btn"
      value="Fridge"
      x-large
      @click="$router.push({ name: 'FridgeIngredient' })"
    >
      <v-img
        src="http://j4a204.p.ssafy.io/img/logo/RECOOK_logo.png"
        style="position:absolute"
        max-width="72"
        contain
        class="mb-8"
      ></v-img>
    </v-btn>

    <v-btn class="bottom-btn" value="Watch">
      <v-icon>mdi-play-circle</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="Mypage">
      <v-icon>mdi-account</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
import { mapState } from 'vuex';
export default {
  components: {},
  props: {},
  data() {
    return {};
  },
  computed: {
    ...mapState({
      pageName: (state) => state.header.pageName,
      user: (state) => state.user.user,
    }),
    selectedView: {
      get() {
        return this.pageName.split('#')[0];
      },
      set(value) {
        if (value === 'Fridge') {
          this.$router.push({ name: 'FridgeIngredient' }).catch(() => {});
          return;
        }
        this.$router.replace({ name: value }).catch(() => {});
      },
    },
  },
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        this.$store.commit('setPageName', value.name);
      },
    },
  },
  methods: {
    fridgeActive() {
      console.log('%cFooter.vue line:97 active!!', 'color: #007acc;');
    },
  },
};
</script>

<style scope>
.v-btn.bottom-btn {
  min-width: 20px !important;
  font-size: 1px;
}
</style>
