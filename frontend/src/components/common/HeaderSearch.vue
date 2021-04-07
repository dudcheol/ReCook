<template>
  <v-app-bar flat color="white" fixed>
    <v-btn icon @click="$router.replace({ path: '/' })">
      <v-icon>mdi-chevron-left</v-icon>
    </v-btn>
    <v-text-field
      ref="search"
      v-model="searchBar"
      placeholder="레시피 이름으로 검색"
      filled
      rounded
      dense
      hide-details
      autofocus
    ></v-text-field>
  </v-app-bar>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {};
  },
  computed: {
    searchBar: {
      get() {
        return this.$store.state.user.searchTitle;
      },
      set(value) {
        this.$store.commit('setSearchTitle', value);
      },
    },
  },
  watch: {},
  methods: {},
  mounted() {
    this.$refs.search.focus();
  },
};
</script>

<style scoped></style>
