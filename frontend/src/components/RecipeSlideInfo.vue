<template>
  <div class="pt-3 px-3">
    <v-row ref="thumbnailBox" class="pb-8">
      <v-col class="d-flex flex-column align-center justify-center">
        <v-img
          height="200"
          width="200"
          :src="data['recipe-main-image']"
          class="rounded-xl elevation-24 my-8"
        >
        </v-img>
        <div
          class="h4 font-weight-bold pt-3 px-8 text-center"
          v-html="$options.filters.spaceCutter(data['recipe-title'])"
        ></div>
        <div
          class="caption-1 grey lighten-3 rounded-xl d-flex align-center justify-center py-1 px-2 mt-2"
        >
          <v-icon small>mdi-timer</v-icon>
          <span class="mr-1">
            {{ data['recipe-time'] }}
          </span>
          <v-icon small>mdi-account-outline</v-icon>
          <span v-if="data['recipe-person']">
            {{ data['recipe-person'].replace('인기준', '인분') }}
          </span>
        </div>
      </v-col>
    </v-row>
    <v-row class="pa-2 grey lighten-4"></v-row>
    <v-row @scroll.passive="onScroll">
      <v-col class="d-flex justify-space-between align-center">
        <div v-if="allergy" class="d-flex align-center">
          <span class="h6 font-weight-black">재료</span>
          <span v-if="hasAllergyOfUser" class="caption-1 pl-2 red--text"
            ><v-icon color="red" small>mdi-alert</v-icon>알러지 재료가 포함된 레시피입니다</span
          >
        </div>
        <!-- <v-btn text x-small class="pa-1">전체보기</v-btn> -->
      </v-col>
    </v-row>
    <v-row>
      <v-col class="pa-0">
        <v-simple-table dense>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">
                  이름
                </th>
                <th class="text-left">
                  양
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in recipeIngredient" :key="item.name">
                <td>{{ item.name }}</td>
                <td>{{ item.quantity }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
    <v-row class="pa-2 grey lighten-4"></v-row>
    <v-row>
      <v-col class="d-flex justify-space-between align-center">
        <span class="h6 font-weight-black">태그</span>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="pt-0">
        <v-chip
          small
          class="mx-1 my-1"
          color="blue-grey lighten-5"
          v-for="(item, index) in hashtag"
          :key="data['recipe-id'] + 'hashtag' + index"
          >{{ item }}</v-chip
        >
      </v-col>
    </v-row>
    <v-row class="pa-2 grey lighten-4"></v-row>
    <v-row>
      <v-col class="d-flex justify-space-between align-center">
        <span class="h6 font-weight-black">리뷰</span>
        <!-- <v-btn text x-small class="pa-1">전체보기</v-btn> -->
      </v-col>
    </v-row>
    <v-row class="pa-2 grey lighten-4"></v-row>
    <v-row>
      <v-col class="d-flex justify-space-between align-center pt-3 px-2 pb-2">
        <span v-if="$store.state.user.user.userId" class="h6 font-weight-regular"
          ><span class="grey lighten-3 rounded-lg black--text pa-1 mr-1 font-weight-black">{{
            $store.state.user.user.userName | truncate(16, '..')
          }}</span
          >님, 이 <strong>레시피</strong>는 어때요?<br /><span
            class="caption d-flex align-center pt-2"
            ><v-icon size="18" class="pr-1" color="grey lighten-1">mdi-information</v-icon>회원님의
            취향과 {{ data['recipe-title'] | truncate(10, '..') }} 관련 추천 레시피입니다</span
          ></span
        >
        <span v-else class="h6 font-weight-regular"
          ><span class="grey lighten-3 rounded-lg black--text pa-1 mr-1 font-weight-black">{{
            data['recipe-title'] | truncate(10, '..')
          }}</span>
          관련 레시피</span
        >
      </v-col>
    </v-row>
    <!-- <v-row>
      <v-col style="height:300px;">
        <div v-if="recipeRecommRelateList.length">
          <RecipeCardList :datas="recipeRecommRelateList" />
        </div>
        <div v-else class="d-flex flex-column justify-center align-center">
          <VueLottiePlayer
            name="survey"
            loop
            height="100%"
            width="200px"
            path="https://assets7.lottiefiles.com/packages/lf20_fefIZO.json"
          />
          <span class="caption font-weight-regular grey--text text--darken-1"
            >현재 레시피와 <span class="font-weight-black dahong--text">관련된 레시피</span>를
            불러오고 있어요</span
          >
        </div>
      </v-col>
    </v-row> -->
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
// import RecipeCardList from '@/components/RecipeCardList.vue';
// import VueLottiePlayer from 'vue-lottie-player';
// import { mapActions, mapState } from 'vuex';
export default {
  // components: { VueLottiePlayer, RecipeCardList },
  components: {},
  props: {
    data: Object,
    hashtag: Array,
  },
  data() {
    return {
      rating: 4.5,
      allergy: true,
    };
  },
  computed: {
    ...mapGetters(['hasAllergyOfUser']),
    // ...mapState({
    //   recipeRecommRelateList: (state) => state.recipe.recipeRecommRelateList,
    // }),
    recipeIngredient: function() {
      const tmp = [];
      const parse = this.parseString(this.data['recipe-ingredient']);
      for (let i = 0; i < parse.length; i += 2) {
        if (!parse[i]) continue;
        tmp.push({ name: parse[i], quantity: parse[i + 1] });
      }
      return tmp;
    },
  },
  watch: {
    // data: {
    //   immediate: true,
    //   handler(value) {
    //     if (this.$store.state.user.user.userId)
    //       this.GET_RECOMM_RECIPE_BY_RECIPETITLE(
    //         value['recipe-title'],
    //         this.$store.state.user.user.userId
    //       );
    //   },
    // },
  },
  methods: {
    // ...mapActions(['GET_RECOMM_RECIPE_BY_RECIPETITLE']),
    onScroll() {
      this.windowTop = window.top.scrollY;
      if (this.windowTop >= this.thumbnailBoxHeight) {
        console.log('%cRecipeDetail.vue line:142', 'color: #007acc;');
      }
    },
    parseString(str) {
      if (str) return str.split('####');
      return [];
    },
  },
  created() {},
  beforeDestroy() {},
};
</script>
<style lang=""></style>
