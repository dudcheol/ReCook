<template>
  <div>
    <RecipeRecommCardItem
      v-for="item in datas"
      :key="'likelist' + item.recipeId"
      :data="item"
      @click="$router.push(`/recipe/${item.recipeId}`)"
      class="mb-4"
    />
  </div>
</template>

<script>
import RecipeRecommCardItem from '@/components/RecipeRecommCardItem';
export default {
  name: 'RecipeRecommCardList',
  components: { RecipeRecommCardItem },
  props: {
    datas: Array,
  },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
