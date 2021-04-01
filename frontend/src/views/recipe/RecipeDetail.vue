<template>
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
      <RecipeSlideItem :imageUrl="recipeImages[index]" :content="item" />
    </swiper-slide>
    <div class="swiper-pagination mt-14" slot="pagination"></div>
  </swiper>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import 'swiper/css/swiper.css';
import RecipeSlideInfo from '@/components/RecipeSlideInfo.vue';
import RecipeSlideItem from '@/components/RecipeSlideItem.vue';
import RecipeSlideThumbnail from '@/components/RecipeSlideThumbnail.vue';

export default {
  components: {
    Swiper,
    SwiperSlide,
    RecipeSlideInfo,
    RecipeSlideItem,
    RecipeSlideThumbnail,
  },
  props: {},
  data() {
    return {
      windowTop: 0,
      swiperOption: {
        autoHeight: true,
        slidesPerView: 1,
        spaceBetween: 30,
        pagination: { el: '.swiper-pagination', type: 'progressbar' },
        navigation: { nextEl: '.swiper-button-next', prevEl: '.swiper-button-prev' },
      },
    };
  },
  computed: {
    ...mapState({
      recipeInfo: (state) => state.recipe.recipeInfo,
      hashtag: (state) => state.hashtag.curRecipeHashtag,
    }),
    thumbnailBoxHeight: function() {
      return this.$refs.thumbnailBox.clientHeight;
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
      handler(value) {
        this.addRecipeById(value.params['recipe_id']);
        this.GET_HASHTAGS_BY_RECIPE_ID(value.params['recipe_id']);
      },
    },
  },
  methods: {
    ...mapActions(['addRecipeById', 'GET_HASHTAGS_BY_RECIPE_ID']),
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
  created() {},
  mounted() {},
  beforeDestroy() {
    this.$store.commit('clearRecipeInfo');
  },
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
