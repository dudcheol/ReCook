<template>
  <v-container fluid class="pb-16 mt-14">
    <v-row>
      <v-col class="pa-0">
        <swiper ref="swiper" class="swiper" :options="swiperOption">
          <swiper-slide v-for="(item, index) in 3" :key="'banner' + index">
            <v-img
              :src="`http://j4a204.p.ssafy.io/img/banner/banner${index + 1}.png`"
              min-height="210"
            ></v-img>
          </swiper-slide>
          <div class="swiper-pagination" slot="pagination"></div>
        </swiper>
      </v-col>
    </v-row>
    <!-- 추천 레시피 -->
    <v-row class="mb-4 py-4 white">
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center pb-5">
            <span class="h6 font-weight-regular d-flex align-center"
              ><span class="px-1 dahong white--text rounded-lg font-weight-black mr-1">{{
                isLogin ? `${$store.state.user.user.userName}` : '추천'
              }}</span>
              {{ isLogin ? '님을 위한' : '' }} 레시피</span
            >
            <!-- <v-btn
              v-if="isLogin && recipeRecommMainList.length"
              text
              small
              class="pa-1"
              @click="$router.push({ path: `recipe/list/recommend` })"
              >전체보기</v-btn
            > -->
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
              <span class="caption-1 font-weight-regular grey--text text--darken-1 pt-4"
                ><span class="font-weight-black dahong--text">추천 레시피</span>를 불러오고
                있어요</span
              >
            </div>
          </v-col>
          <v-col v-else class="py-0 px-4">
            <v-sheet
              rounded="xl"
              class="pa-4 text-center caption-1 grey--text"
              @click="$router.push({ path: '/login' })"
              ><span class="dahong--text"><u>로그인</u></span
              >이 필요한 서비스입니다</v-sheet
            >
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <!-- 인기 레시피 -->
    <v-row class="pt-2 pb-4 mb-4 white">
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center">
            <span class="h6 font-weight-regular d-flex align-center"
              ><v-avatar color="red lighten-5" size="32" class="mr-1"
                ><v-icon color="dahong">mdi-fire</v-icon></v-avatar
              ><strong>인기</strong> 레시피</span
            >
            <v-btn icon small class="pa-1" @click="$router.push({ path: `recipe/list/popular` })"
              ><v-icon>mdi-chevron-right</v-icon></v-btn
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
    <v-row class="pt-2 pb-4 white">
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center">
            <span class="h6 font-weight-regular d-flex align-center"
              ><v-avatar color="amber lighten-5" size="32" class="mr-1"
                ><v-icon color="yellow">mdi-new-box</v-icon></v-avatar
              ><strong>최신</strong> 레시피</span
            >
            <v-btn icon small class="pa-1" @click="$router.push({ path: `recipe/list/recent` })"
              ><v-icon>mdi-chevron-right</v-icon></v-btn
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

    <v-bottom-sheet v-model="beginAlert" persistent>
      <v-card class="rounded-t-xl pa-4 text-center">
        <v-btn
          icon
          color="black"
          right
          x-large
          style="position:absolute; right:2px; top:2px; z-index:3"
          @click="$store.commit('isBeginAlertReaded', true)"
          ><v-icon>mdi-close</v-icon></v-btn
        >
        <v-img
          src="http://j4a204.p.ssafy.io/img/image/write_review.png"
          max-height="375"
          contain
        ></v-img>
        <div class="pa-2 h4 font-weight-thin">
          <span class="font-weight-black">리뷰</span>를 작성해보세요
        </div>
        <div class="font-weight-regular caption-1">
          레시피에 리뷰를 작성할수록<br />
          더 정확한 <strong>개인별 맞춤 레시피</strong> 추천이 가능합니다<br />
          레시피에서 <v-icon size="14" color="black">mdi-pencil</v-icon> 버튼을 클릭해 보세요!
        </div>
      </v-card>
    </v-bottom-sheet>
  </v-container>
</template>

<script>
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import RecipeCardList from '@/components/RecipeCardList.vue';
import VueLottiePlayer from 'vue-lottie-player';
import { mapActions, mapState } from 'vuex';
export default {
  components: { VueLottiePlayer, RecipeCardList, Swiper, SwiperSlide },
  props: {},
  data() {
    return {
      isLogin: false,
      swiperOption: {
        observer: true,
        observeParents: true,
        autoHeight: false,
        slidesPerView: 1,
        spaceBetween: 0,
        pagination: { el: '.swiper-pagination', clickable: true },
        autoplay: {
          delay: 5000,
        },
        loop: true,
      },
    };
  },
  computed: {
    ...mapState({
      recipeNewList: (state) => state.recipe.recipeNewList,
      recipeHotList: (state) => state.recipe.recipeHotList,
      recipeRecommMainList: (state) => state.recipe.recipeRecommMainList,
    }),
    beginAlert: {
      get() {
        if (this.$store.state.user.user.userId) return !this.$store.state.user.beginAlert;
        return false;
      },
    },
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

<style>
:root {
  --swiper-theme-color: white;
}
</style>
