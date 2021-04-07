<template>
  <div class="grey lighten-4 px-4 pb-4">
    <RecipeRecommCardItem
      v-for="item in list"
      :key="item.recipeId"
      :data="item"
      class="mt-4"
      @click="$router.push({ path: `/recipe/${item.recipeId || item['recipe-id']}` })"
    />
    <infinite-loading ref="InfiniteLoading" @infinite="infiniteHandler">
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
import { getRecipeNewListAll, getRecipeHotListAll } from '@/api/recipe';
import RecipeRecommCardItem from '@/components/RecipeRecommCardItem.vue';
export default {
  components: { RecipeRecommCardItem },
  props: {},
  data() {
    return {
      page: 0,
      size: 5,
      list: [],
      onLoad: false,
    };
  },
  computed: {},
  watch: {
    $route: {
      immediate: true,
      handler() {
        // console.log(
        //   '%cRecipeList.vue line:39 this.$refs.InfiniteLoading',
        //   'color: #007acc;',
        //   this.$refs.InfiniteLoading
        // );
      },
    },
  },
  methods: {
    infiniteHandler($state) {
      switch (this.$route.path.split('/')[3]) {
        case 'popular':
          getRecipeHotListAll(
            this.page,
            this.size,
            (response) => {
              const data = response.data.content;
              if (data.length) {
                this.page += 1;
                this.list.push(...data);
                $state.loaded();
              } else {
                $state.complete();
              }
            },
            () => {
              $state.error();
            }
          );
          break;
        case 'recent':
          getRecipeNewListAll(
            this.page,
            this.size,
            (response) => {
              const data = response.data.content;
              if (data.length) {
                this.page += 1;
                this.list.push(...data);
                $state.loaded();
              } else {
                $state.complete();
              }
            },
            () => {
              $state.error();
            }
          );
          break;
        default:
      }
    },
  },
  mounted() {
    this.$refs.InfiniteLoading.stateChanger.reset();
  },
};
</script>

<style scoped></style>
