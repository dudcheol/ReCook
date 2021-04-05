<template>
  <div style="height:100%" class="grey lighten-4">
    <v-container fluid class="white rounded-b-xl elevation-24" style="position:fixed; z-index:1">
      <v-row>
        <v-col cols="12" class="d-flex align-center justify-space-between pt-1 pb-2">
          <span class="text-h5 font-weight-black pl-2">나의 냉장고</span>
          <v-checkbox
            class="px-0 ma-0"
            color="dahong"
            label="알러지 제외"
            :input-value="$store.state.user.allergyCheck"
            @change="onAllergyCheckClick"
            hide-details
          ></v-checkbox>
        </v-col>
        <v-col cols="12" class="pb-3 pt-0">
          <v-row>
            <v-col cols="10" class="pb-2 pt-1">
              <v-chip-group :column="isExpanded">
                <v-chip
                  v-for="item in $store.state.user.selectedIngredients"
                  :key="'ingredients' + item.smallId"
                  color="yellow"
                  class="pt-1"
                >
                  {{ item.smallName }}
                </v-chip>
              </v-chip-group>
            </v-col>
            <v-col class="d-flex align-center justify-end">
              <v-btn icon @click="isExpanded = !isExpanded">
                <v-icon>mdi-chevron-{{ isExpanded ? 'up' : 'down' }}</v-icon>
              </v-btn>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <div class="pt-10 mt-14 px-4 pb-4" v-if="$store.state.recipe.recipeRecomm.length">
      <RecipeRecommCardItem
        v-for="item in $store.state.recipe.recipeRecomm"
        :key="item.recipeId"
        :data="item"
        class="mt-4"
        @click="$router.push({ path: `/recipe/${item.recipeId}` })"
      />
    </div>
    <div v-else style="height:100%" class="d-flex flex-column align-center justify-center">
      <MessageEmpty
        :height="200"
        :width="200"
        :text="'추천 결과를 찾지 못했어요'"
        style="width:100%"
      />
      <span class="mx-14 my-2 px-5 py-2 text-caption rounded-xl grey lighten-2"
        >너무 많은 재료를 선택하거나, 관련없는 재료를 선택하면 추천이 안될 수 있습니다</span
      >
    </div>
  </div>
</template>

<script>
import RecipeRecommCardItem from '@/components/RecipeRecommCardItem.vue';
import { mapActions } from 'vuex';
import MessageEmpty from '@/components/common/MessageEmpty.vue';
export default {
  components: { RecipeRecommCardItem, MessageEmpty },
  props: {},
  data() {
    return {
      isExpanded: false,
    };
  },
  computed: {},
  watch: {
    $route: {
      immediate: true,
      handler() {
        this.getRecommRecipe();
      },
    },
  },
  methods: {
    ...mapActions(['GET_RECOMM_RECIPE_LIST_BY_INGREDIENTS']),
    getRecommRecipe() {
      this.GET_RECOMM_RECIPE_LIST_BY_INGREDIENTS({
        ingredientList: this.$store.state.user.selectedIngredients,
        userId: this.$store.state.user.user.userId ? this.$store.state.user.user.userId : '',
        allergy: this.$store.state.user.allergyCheck,
      });
    },
    onAllergyCheckClick() {
      if (this.$store.state.user.user.userId) {
        this.$store.commit('toggleAllergyCheck');
        this.getRecommRecipe();
      } else {
        this.$router.push({ path: `/login` });
      }
    },
  },
};
</script>

<style scoped></style>
