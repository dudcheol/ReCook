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
            <span class="text-h6 font-weight-regular"
              ><span class="pa-1 grey lighten-3 rounded-lg font-weight-black">{{
                isLogin ? `${$store.state.user.user.userName}` : '추천'
              }}</span>
              {{ isLogin ? '님을 위한' : '' }} 레시피</span
            >
            <v-btn
              v-if="isLogin && recipeRecommMainList.length"
              text
              small
              class="pa-1"
              @click="$router.push({ path: `recipe/list/recommend` })"
              >전체보기</v-btn
            >
          </v-col>
        </v-row>
        <v-row>
          <v-col v-if="isLogin" class="pa-0">
            <RecipeCardList
              v-if="recipeRecommMainList.length"
              :datas="recipeRecommMainList"
              :type="'recommend'"
            />
            <div v-else class="d-flex flex-column justify-center align-center pb-4">
              <VueLottiePlayer
                name="survey"
                loop
                height="100%"
                width="200px"
                path="https://assets7.lottiefiles.com/packages/lf20_fefIZO.json"
              />
              <span class="caption font-weight-regular grey--text text--darken-1"
                ><span class="font-weight-black dahong--text">추천 레시피</span>를 불러오고
                있어요</span
              >
            </div>
          </v-col>
          <v-col v-else class="py-0 px-4">
            <v-sheet
              rounded="xl"
              color="grey lighten-4"
              class="pa-4 text-center caption grey--text"
              @click="$router.push({ path: '/login' })"
              >추천 레시피를 보려면 <span class="dahong--text"><u>로그인</u></span
              >이 필요합니다</v-sheet
            >
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <!-- 인기 레시피 -->
    <v-row class="pb-4">
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center">
            <span class="text-h6 font-weight-regular"><strong>인기</strong> 레시피</span>
            <v-btn text small class="pa-1" @click="$router.push({ path: `recipe/list/popular` })"
              >전체보기</v-btn
            >
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <RecipeCardList :datas="recipeHotList" :type="'popular'" />
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <!-- 최신 레시피 -->
    <v-row>
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center">
            <span class="text-h6 font-weight-regular"><strong>최신</strong> 레시피</span>
            <v-btn text small class="pa-1" @click="$router.push({ path: `recipe/list/recent` })"
              >전체보기</v-btn
            >
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <RecipeCardList :datas="recipeNewList" :type="'recent'" />
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import RecipeCardList from '@/components/RecipeCardList.vue';
import VueLottiePlayer from 'vue-lottie-player';
import { mapActions, mapState } from 'vuex';
export default {
  components: { VueLottiePlayer, RecipeCardList },
  props: {},
  data() {
    return {
      isLogin: false,
    };
  },
  computed: {
    ...mapState({
      recipeNewList: (state) => state.recipe.recipeNewList,
      recipeHotList: (state) => state.recipe.recipeHotList,
      recipeRecommMainList: (state) => state.recipe.recipeRecommMainList,
    }),
  },
  watch: {
    '$store.state.user.user': {
      immediate: true,
      handler(value) {
        if (value.userId) {
          this.isLogin = true;
          this.GET_RECOMM_RECIPE_LIST_BY_USER(value.userId);
        } else {
          this.isLogin = false;
        }
      },
    },
  },
  methods: {
    ...mapActions(['addRecipeNewList', 'addRecipeHotList', 'GET_RECOMM_RECIPE_LIST_BY_USER']),
  },
  created() {
    this.addRecipeNewList();
    this.addRecipeHotList();
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
