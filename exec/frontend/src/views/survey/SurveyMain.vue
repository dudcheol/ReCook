<template>
  <v-container>
    <v-row class="px-4">
      <v-col cols="12">
        <v-avatar size="48">
          <v-img :src="user.userImage"></v-img>
        </v-avatar>
        <span class="pl-2 font-weight-medium">{{ user.userName }}</span
        >님,
      </v-col>
      <v-col cols="12">
        <span class="h2 font-weight-thin"
          ><span class="font-weight-black">취향</span>을<br />
          알려주세요</span
        >
      </v-col>
      <v-col cols="12" class="d-flex align-center justify-center pa-0">
        <VueLottiePlayer
          name="survey"
          loop
          height="100%"
          width="100%"
          path="https://assets7.lottiefiles.com/private_files/lf30_ltuqrtmn.json"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import VueLottiePlayer from 'vue-lottie-player';
import { mapState } from 'vuex';
export default {
  components: { VueLottiePlayer },
  props: {},
  data() {
    return {};
  },
  computed: {
    ...mapState({
      user: (state) => state.user.user,
    }),
  },
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
