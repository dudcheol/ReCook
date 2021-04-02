<template>
  <div>
    <h1>RecipeList</h1>
    <infinite-loading @infinite="infiniteHandler">
      <div slot="spinner">
        <v-skeleton-loader class="mx-auto px-4" max-width="100%" type="card"></v-skeleton-loader>
      </div>
      <div slot="no-results"></div>
      <div slot="no-more"></div>
      <div slot="error"></div>
    </infinite-loading>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      page: 0,
      size: 5,
      list: [],
    };
  },
  computed: {},
  watch: {},
  methods: {
    infiniteHandler($state) {
      console.log('%cRecipeList.vue line:32 $state', 'color: #007acc;', $state);
    },
  },
};
</script>

<style scoped></style>
