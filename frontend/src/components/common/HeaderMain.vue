<template>
  <v-app-bar flat color="white" fixed>
    <v-app-bar-nav-icon></v-app-bar-nav-icon>
    <v-spacer></v-spacer>
    <v-toolbar-title>
      <!-- <div class="d-flex align-center justify-center"> -->
      <!-- <v-img :src="'http://j4a204.p.ssafy.io/img/logo/RECOOK_logo.png'" max-width="70"></v-img> -->
      <span class="font-weight-black dahong--text">
        <!-- <v-avatar size="36" color="red lighten-5">
          <v-icon color="dahong">mdi-chef-hat</v-icon>
        </v-avatar> -->
        <!-- <span class="pl-1">REC</span><span class="yellow--text">OO</span>K -->
      </span>
      <v-img
        src="http://j4a204.p.ssafy.io/img/logo/recook_ppt_logo.png"
        contain
        max-height="24"
      ></v-img>
      <!-- </div> -->
      <!-- <span class="text-uppercase font-weight-black">recook</span> -->
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
