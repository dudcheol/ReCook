<template>
  <v-app-bar flat color="white" fixed>
    <div class="mx-auto">
      <v-btn
        rounded
        depressed
        class="text-subtitle-1"
        :color="toggle ? 'dahong' : 'white'"
        :class="toggle ? 'font-weight-black white--text' : 'font-weight-medium'"
        @click="toggle = !toggle"
        >리뷰</v-btn
      >
      <v-btn
        rounded
        depressed
        class="text-subtitle-1"
        :color="!toggle ? 'dahong' : 'white'"
        :class="!toggle ? 'font-weight-black white--text' : 'font-weight-medium'"
        @click="toggle = !toggle"
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
      toggle: true,
    };
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
