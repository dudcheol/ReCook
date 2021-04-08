<template>
  <v-app-bar flat color="white" fixed>
    <v-app-bar-nav-icon @click="$store.commit('changeDrawerState', true)"></v-app-bar-nav-icon>
    <v-spacer></v-spacer>
    <v-toolbar-title>
      <span class="font-weight-black dahong--text"> </span>
      <v-img
        src="http://j4a204.p.ssafy.io/img/logo/recook_ppt_logo.png"
        contain
        max-height="24"
      ></v-img>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn icon @click="$router.replace({ path: 'search' })">
      <v-icon>mdi-magnify</v-icon>
    </v-btn>
  </v-app-bar>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
