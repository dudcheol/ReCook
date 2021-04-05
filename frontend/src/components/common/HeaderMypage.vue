<template>
  <v-app-bar flat color="white" fixed>
    <div class="mx-auto">
      <v-btn
        rounded
        depressed
        class="subtitle-1"
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
        class="subtitle-1"
        :color="!toggle ? 'dahong' : 'white'"
        :class="!toggle ? 'font-weight-black white--text' : 'font-weight-medium'"
        @click="
          $router.replace({ path: `/${$route.params['user_name']}/like` }).catch(() => {});
          $store.commit('setMypageTabState', 'like');
        "
        >찜</v-btn
      >
      <v-btn icon color="grey" small absolute right class="mt-1" @click="dialog = true">
        <v-icon size="20">mdi-logout</v-icon>
      </v-btn>
    </div>
    <v-dialog v-model="dialog" max-width="290">
      <v-card rounded="xl">
        <v-card-title class="headline">
          알림
        </v-card-title>
        <v-card-text>로그아웃할까요? 🥺</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="dahong" text @click="dialog = false">
            아니오
          </v-btn>
          <v-btn
            color="dahong"
            text
            @click="
              dialog = false;
              $store.dispatch('LOGOUT');
            "
          >
            네
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-app-bar>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      path: '',
      dialog: false,
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
