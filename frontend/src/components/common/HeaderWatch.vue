<template>
  <v-app-bar flat color="white" fixed>
    <v-row>
      <v-col cols="1" class="pa-0 d-flex align-center">
        <v-btn v-if="isDetail" icon @click="$router.go(-1)">
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
      </v-col>
      <v-col cols="10" class="pa-0 d-flex justify-center align-center">
        <span v-if="!isDetail" class="text-uppercase font-weight-black" style="font-size:20px"
          >Watch</span
        >
      </v-col>
      <v-col cols="1" class="pa-0"></v-col>
    </v-row>
  </v-app-bar>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      isDetail: false,
    };
  },
  computed: {},
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        this.isDetail = value.path !== '/watch';
      },
    },
  },
  methods: {},
};
</script>

<style scoped></style>
