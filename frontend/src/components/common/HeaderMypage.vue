<template>
  <v-app-bar flat color="white" fixed>
    <div class="mx-auto">
      <v-btn
        rounded
        depressed
        class="text-subtitle-1"
        :color="toggle ? 'dahong' : 'white'"
        :class="toggle ? 'font-weight-black white--text' : 'font-weight-medium'"
        @click="
          $router.replace({ path: `/${$route.params['user_name']}/review` }).catch(() => {});
          $store.commit('setMypageTabState', 'review');
        "
        >리뷰</v-btn
      >
      <v-btn
        rounded
        depressed
        class="text-subtitle-1"
        :color="!toggle ? 'dahong' : 'white'"
        :class="!toggle ? 'font-weight-black white--text' : 'font-weight-medium'"
        @click="
          $router.replace({ path: `/${$route.params['user_name']}/like` }).catch(() => {});
          $store.commit('setMypageTabState', 'like');
        "
        >찜</v-btn
      >
    </div>
  </v-app-bar>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      path: '',
    };
  },
  computed: {
    toggle() {
      return this.path === 'review';
    },
  },
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        this.path = value.path.split('/')[2];
      },
    },
  },
  methods: {},
};
</script>

<style scoped></style>
