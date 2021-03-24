<template>
  <v-app-bar flat color="white">
    <v-app-bar-nav-icon></v-app-bar-nav-icon>
    <v-spacer></v-spacer>
    <v-toolbar-title>
      <span class="text-uppercase font-weight-black">recook</span>
    </v-toolbar-title>
    <v-spacer></v-spacer>

    <v-btn icon>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>
  </v-app-bar>
</template>

<script>
export default {
  components: {},
  props: {
    type: String,
  },
  data() {
    return {};
  },
  computed: {},
  watch: {
    '$route.params.type': {
      immediate: true,
      handler(type) {
        switch (type) {
          case 'main':
            break;
          case 'fridge-recomm':
            break;
          case 'recipe-detail':
            break;
          case 'review-detail':
            break;
          case 'profile-edit':
            break;
          default:
        }
      },
    },
  },
  methods: {},
};
</script>

<style lang="scss" scoped></style>
