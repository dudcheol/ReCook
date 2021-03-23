<template>
  <v-app-bar flat color="white">
    <v-row>
      <v-col cols="2" class="pa-0">
        <v-btn icon @click="$router.go(-1)">
          <v-icon>mdi-{{ leftBtn }}</v-icon>
        </v-btn>
      </v-col>
      <v-col class="d-flex justify-space-around">
        <span class="font-weight-medium text-subtitle-1">{{ title }}</span>
      </v-col>
      <v-col cols="2" class="d-flex justify-end">
        <v-icon>mdi-{{ rightBtn }}</v-icon>
      </v-col>
    </v-row>
  </v-app-bar>
</template>

<script>
export default {
  components: {},
  props: {
    leftBtn: String,
    rightBtn: String,
    title: String,
  },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style lang="scss" scoped></style>
