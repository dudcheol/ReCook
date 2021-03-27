<template>
  <v-container fluid>
    <v-row>
      <v-col class="pa-0">
        <div class="banner">banner area</div>
      </v-col>
    </v-row>
    <!-- 추천 레시피 -->
    <v-row class="pb-4">
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center">
            <span class="text-h5 font-weight-black">추천 Recipe</span>
            <v-btn text small class="pa-1">전체보기</v-btn>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <RecipeCardList :datas="recipeNewList" />
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <!-- 인기 레시피 -->
    <v-row class="pb-4">
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center">
            <span class="text-h5 font-weight-black">인기 Recipe</span>
            <v-btn text small class="pa-1">전체보기</v-btn>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <RecipeCardList :datas="recipeNewList" />
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <!-- 최신 레시피 -->
    <v-row>
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center">
            <span class="text-h5 font-weight-black">최신 Recipe</span>
            <v-btn text small class="pa-1">전체보기</v-btn>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <RecipeCardList :datas="recipeNewList" />
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import RecipeCardList from '@/components/RecipeCardList.vue';
import { mapActions, mapState } from 'vuex';
export default {
  components: { RecipeCardList },
  props: {},
  data() {
    return {};
  },
  computed: {
    ...mapState({
      recipeNewList: (state) => state.recipe.recipeNewList,
    }),
  },
  watch: {},
  methods: {
    ...mapActions(['addRecipeNewList']),
  },
  mounted() {
    this.addRecipeNewList();
  },
};
</script>

<style scoped>
.banner {
  height: 128px;
  background-color: lightgrey;
}
.recipe-box {
  height: 196px;
  background-color: lightgrey;
}
</style>
