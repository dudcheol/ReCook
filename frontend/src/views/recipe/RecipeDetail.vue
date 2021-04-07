<template>
  <div>
    <swiper ref="swiper" class="swiper" :options="swiperOption">
      <swiper-slide>
        <RecipeSlideThumbnail
          :imageUrl="recipeInfo['recipe-main-image']"
          :title="recipeInfo['recipe-title']"
          :time="recipeInfo['recipe-time']"
          :person="recipeInfo['recipe-person']"
        />
      </swiper-slide>
      <swiper-slide class="py-3">
        <RecipeSlideInfo :data="recipeInfo" :hashtag="hashtag" :review="recipeReviews" />
      </swiper-slide>
      <swiper-slide v-for="(item, index) in recipeContext" :key="item['recipe_id']">
        <RecipeSlideItem
          v-if="index != recipeContext.length - 1"
          :imageUrl="recipeImages[index]"
          :content="item"
        />
        <div
          v-else
          :style="{ minHeight: windowHeight + 'px' }"
          class="d-flex align-center justify-center"
        >
          <div
            class="d-flex align-center justify-center flex-column py-8"
            style="height:100%; width:100%; overflow:auto"
          >
            <v-img
              height="120"
              width="120"
              :src="recipeInfo['recipe-main-image']"
              class="rounded-xl elevation-24 mb-4"
            >
            </v-img>
            <div
              class="h5 font-weight-bold py-3 px-8 text-center"
              v-html="$options.filters.spaceCutter(recipeInfo['recipe-title'] || '')"
            ></div>
            <span class="h6 pb-2 font-weight-regular">레시피를 모두 보셨습니다</span>
            <v-btn dark depressed rounded @click="swiper.slideTo(0)">첫 페이지로 가기</v-btn>
          </div>
          <div
            class="font-weight-thin h5 pa-0 pl-3"
            style="position:absolute; bottom:0px; left:0px"
          >
            이런 <strong>레시피</strong>는 어때요?
          </div>
        </div>
      </swiper-slide>
      <div class="swiper-pagination" slot="pagination"></div>
    </swiper>
    <v-row v-if="recommShow">
      <v-col style="height:258px">
        <div v-if="recipeRecommRelateList.length">
          <RecipeCardList
            :datas="recipeRecommRelateList"
            @click="swiper.slideTo(0)"
            :total="true"
          />
        </div>
        <div v-else class="d-flex flex-column justify-center align-center">
          <div class="d-flex flex-column justify-center align-center">
            <VueLottiePlayer
              name="survey"
              loop
              height="100%"
              width="200px"
              path="https://assets7.lottiefiles.com/packages/lf20_fefIZO.json"
            />
            <span class="caption-1 font-weight-regular grey--text text--darken-1 pt-4 text-center"
              ><strong class="black--text">{{
                recipeInfo['recipe-title'] | truncate(14, '..')
              }}</strong
              >와(과) 관련된<br /><span v-if="$store.state.user.user.userId">회원님만을 위한</span
              ><span class="font-weight-black dahong--text"> 추천 레시피</span>를 불러오고
              있어요</span
            >
          </div>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import 'swiper/css/swiper.css';
import RecipeSlideInfo from '@/components/RecipeSlideInfo.vue';
import RecipeSlideItem from '@/components/RecipeSlideItem.vue';
import RecipeSlideThumbnail from '@/components/RecipeSlideThumbnail.vue';
import RecipeCardList from '@/components/RecipeCardList.vue';
import VueLottiePlayer from 'vue-lottie-player';

export default {
  components: {
    Swiper,
    SwiperSlide,
    RecipeSlideInfo,
    RecipeSlideItem,
    RecipeSlideThumbnail,
    RecipeCardList,
    VueLottiePlayer,
  },
  props: {},
  data() {
    return {
      windowTop: 0,
      swiperOption: {
        observer: true,
        observeParents: true,
        autoHeight: true,
        slidesPerView: 1,
        spaceBetween: 30,
        pagination: { el: '.swiper-pagination', type: 'progressbar' },
        navigation: { nextEl: '.swiper-button-next', prevEl: '.swiper-button-prev' },
      },
      swiper: null,
      windowHeight: 0,
    };
  },
  computed: {
    ...mapState({
      recipeInfo: (state) => state.recipe.recipeInfo,
      hashtag: (state) => state.hashtag.curRecipeHashtag,
      recipeRecommRelateList: (state) => state.recipe.recipeRecommRelateList,
      recipeReviews: (state) => state.recipe.recipeReviews,
    }),
    thumbnailBoxHeight() {
      return this.$refs.thumbnailBox.clientHeight;
    },
    recommShow() {
      if (this.swiper)
        return (
          this.swiper.realIndex === 1 || this.swiper.realIndex === this.recipeContext.length + 1
        );
      else return false;
    },
    recipeContext() {
      return this.parseString(this.recipeInfo['recipe-context']);
    },
    recipeImages() {
      return this.parseString(this.recipeInfo['recipe-image']);
    },
  },
  watch: {
    $route: {
      immediate: true,
      async handler(value) {
        const recipeId = value.params['recipe_id'];
        if (this.recipeInfo['recipe-id'] != recipeId) {
          this.$store.commit('clearRecipeInfo');
          this.$store.commit('clearRecipeRecommRelateList');
          await this.addRecipeById(recipeId);
          this.GET_HASHTAGS_BY_RECIPE_ID(recipeId);
          this.GET_REVIEW_BY_RECIPEID(recipeId);
          this.GET_RECOMM_RECIPE_BY_RECIPETITLE({
            recipeTitle: this.recipeInfo['recipe-title'],
            userId: this.$store.state.user.user.userId,
          });
        }
      },
    },
  },
  methods: {
    ...mapActions([
      'addRecipeById',
      'GET_HASHTAGS_BY_RECIPE_ID',
      'GET_RECOMM_RECIPE_BY_RECIPETITLE',
      'GET_REVIEW_BY_RECIPEID',
    ]),
    parseString(str) {
      if (str) return str.split('####');
      return [];
    },
    onSwiper(swiper) {
      console.log(swiper);
    },
    onSlideChange() {
      console.log('slide change');
    },
  },
  mounted() {
    this.swiper = this.$refs.swiper.$swiper;
    this.windowHeight = window.innerHeight - 56 - 246;
  },
  beforeDestroy() {},
};
</script>

<style>
.swiper-pagination-progressbar {
  background: #f8f9fb;
}
.swiper-pagination-progressbar .swiper-pagination-progressbar-fill {
  /* background: #ff4b3a; */
  background: #ff5f2e;
}
</style>
