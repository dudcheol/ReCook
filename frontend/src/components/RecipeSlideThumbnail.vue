<template>
  <div :style="{ minHeight: windowHeight + 'px' }" class="d-flex flex-column black">
    <v-img
      :src="imageUrl"
      gradient="to bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.8)"
      class="d-flex align-end white--text"
    >
      <template v-slot:placeholder>
        <v-row class="fill-height ma-0" align="center" justify="center">
          <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
        </v-row>
      </template>
      <div
        :style="{ height: (windowHeight / 5) * 3 + 'px' }"
        class="d-flex flex-column justify-space-between"
      >
        <div class="d-flex flex-column align-end pr-2">
          <VueLottiePlayer
            name="swipe"
            loop
            height="100px"
            width="100px"
            path="https://assets5.lottiefiles.com/packages/lf20_C2NTmv.json"
          />
          <div class="font-weight-medium sub-title-2">
            밀어서 레시피 보기
          </div>
        </div>

        <div class="d-flex flex-column pa-2">
          <span
            class="h2 font-weight-thin"
            v-html="$options.filters.spaceCutter(title || '')"
          ></span>
          <div class="pl-2 pt-2">
            <v-icon small color="white">mdi-timer</v-icon>
            <span>
              {{ time }}
            </span>
            <v-icon small color="white">mdi-account-outline</v-icon>
            <span v-if="person">
              {{ person.replace('인기준', '인분') }}
            </span>
          </div>
        </div>
      </div>
    </v-img>
  </div>
</template>
<script>
import VueLottiePlayer from 'vue-lottie-player';

export default {
  components: {
    VueLottiePlayer,
  },
  props: {
    imageUrl: String,
    title: String,
    time: String,
    person: String,
  },
  data() {
    return {
      windowHeight: 0,
    };
  },
  computed: {},
  watch: {},
  methods: {},
  mounted() {
    this.windowHeight = window.innerHeight - 56;
  },
};
</script>
<style></style>
