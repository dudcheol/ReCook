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
        <RecipeSlideInfo :data="recipeInfo" :hashtag="hashtag" />
      </swiper-slide>
      <swiper-slide v-for="(item, index) in recipeContext" :key="item['recipe_id']">
        <RecipeSlideItem
          v-if="index != recipeContext.length - 1"
          :imageUrl="recipeImages[index]"
          :content="item"
        />
        <v-container v-else :style="{ minHeight: windowHeight + 'px' }">
          <v-row>
            <v-col cols="12" class="h4 mt-4">모두 보셨습니다</v-col>
            <v-col class="pa-0 pl-3">
              <v-btn dark depressed rounded @click="swiper.slideTo(0)">첫 페이지로 가기</v-btn>
            </v-col>
            <v-col class="font-weight-thin h5" style="position:absolute; bottom:0px"
              >이런 <strong>레시피</strong>는 어때요?
            </v-col>
          </v-row>
        </v-container>
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
          <VueLottiePlayer
            name="survey"
            loop
            height="100%"
            width="200px"
            path="https://assets7.lottiefiles.com/packages/lf20_fefIZO.json"
          />
          <span class="caption-1 font-weight-regular grey--text text--darken-1"
            >현재 레시피와 <span class="font-weight-black dahong--text">관련된 추천 레시피</span>를
            불러오고 있어요</span
          >
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
    }),
    thumbnailBoxHeight: function() {
      return this.$refs.thumbnailBox.clientHeight;
    },
    recommShow() {
      if (this.swiper)
        return (
          this.swiper.realIndex === 1 || this.swiper.realIndex === this.recipeContext.length + 1
        );
      else return false;
    },
    // recipeInfo: function() {
    //   const ret = this.recipeNewList.filter((v) => {
    //     return v['recipe-id'] == this.$route.params.id;
    //   });
    //   return ret[0];
    // },
    // recipeIngredient: function() {
    //   const tmp = [];
    //   const parse = this.parseString(this.recipeInfo['recipe-ingredient']);
    //   for (let i = 0; i < parse.length; i += 2) {
    //     if (!parse[i]) continue;
    //     tmp.push({ name: parse[i], quantity: parse[i + 1] });
    //   }
    //   return tmp;
    // },
    recipeContext: function() {
      return this.parseString(this.recipeInfo['recipe-context']);
    },
    recipeImages: function() {
      return this.parseString(this.recipeInfo['recipe-image']);
    },
  },
  watch: {
    $route: {
      immediate: true,
      async handler(value) {
        // this.addRecipeById(value.params['recipe_id']);
        // this.GET_HASHTAGS_BY_RECIPE_ID(value.params['recipe_id']);
        if (this.recipeInfo['recipe-id'] != value.params['recipe_id']) {
          this.$store.commit('clearRecipeInfo');
          this.$store.commit('clearRecipeRecommRelateList');
          await this.addRecipeById(value.params['recipe_id']);
          this.GET_HASHTAGS_BY_RECIPE_ID(value.params['recipe_id']);
          if (this.$store.state.user.user.userId) {
            this.GET_RECOMM_RECIPE_BY_RECIPETITLE(
              this.recipeInfo['recipe-title'],
              this.$store.state.user.user.userId
            );
          }
        }
      },
    },
    // 'recipeInfo["recipe-title"]': {
    //   handler(value) {
    //     if (this.$store.state.user.user.userId) {
    //       this.GET_RECOMM_RECIPE_BY_RECIPETITLE(
    //         // value['recipe-title'],
    //         value,
    //         this.$store.state.user.user.userId
    //       );
    //     }
    //   },
    // },
  },
  methods: {
    ...mapActions([
      'addRecipeById',
      'GET_HASHTAGS_BY_RECIPE_ID',
      'GET_RECOMM_RECIPE_BY_RECIPETITLE',
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
  async created() {
    // if (this.recipeInfo['recipe-id'] != this.$route.params['recipe_id']) {
    //   this.$store.commit('clearRecipeInfo');
    //   this.$store.commit('clearRecipeRecommRelateList');
    //   await this.addRecipeById(this.$route.params['recipe_id']);
    //   this.GET_HASHTAGS_BY_RECIPE_ID(this.$route.params['recipe_id']);
    //   if (this.$store.state.user.user.userId) {
    //     this.GET_RECOMM_RECIPE_BY_RECIPETITLE(
    //       this.recipeInfo['recipe-title'],
    //       this.$store.state.user.user.userId
    //     );
    //   }
    // }
  },
  mounted() {
    this.swiper = this.$refs.swiper.$swiper;
    this.windowHeight = window.innerHeight - 56 - 268;
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
