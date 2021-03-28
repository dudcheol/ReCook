<template>
  <v-container fluid>
    <v-row ref="thumbnailBox">
      <v-col>
        <v-col class="d-flex flex-column align-center justify-center">
          <v-img height="200" width="200" :src="recipeInfo['recipe-main-image']" class="rounded-xl">
          </v-img>
          <div class="text-h5 font-weight-bold pt-2">{{ recipeInfo['recipe-title'] }}</div>
          <div>
            <v-chip
              small
              class="ml-1"
              color="blue-grey lighten-5"
              v-for="(item, index) in hashtag"
              :key="recipeInfo['recipe-id'] + 'hashtag' + index"
              >{{ item }}</v-chip
            >
          </div>
          <div class="text-caption pt-2">
            <v-icon small>mdi-timer</v-icon>
            <span>
              {{ recipeInfo['recipe-time'] }}
            </span>
            <v-icon small>mdi-account-outline</v-icon>
            <span>
              {{ recipeInfo['recipe-person'].replace('인기준', '인분') }}
            </span>
          </div>
        </v-col>
      </v-col>
    </v-row>
    <v-row @scroll.passive="onScroll">
      <v-col class="d-flex justify-space-between align-center">
        <div v-if="allergy" class="d-flex align-center">
          <span class="text-h6 font-weight-black">재료</span>
          <span class="text-caption pl-2 red--text"
            ><v-icon color="red" small>mdi-alert</v-icon>알러지 재료가 포함된 레시피입니다</span
          >
        </div>
        <v-btn text x-small class="pa-1">전체보기</v-btn>
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
    <v-row>
      <v-col class="d-flex justify-space-between align-center">
        <span class="text-h6 font-weight-black">레시피</span>
        <v-btn text x-small class="pa-1">전체보기</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        {{ recipeContext }}
      </v-col>
    </v-row>
    <v-row>
      <v-col class="d-flex justify-space-between align-center">
        <span class="text-h6 font-weight-black">리뷰</span>
        <v-btn text x-small class="pa-1">전체보기</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="d-flex justify-space-between align-center">
        <span class="text-h6 font-weight-black">추천</span>
        <v-btn text x-small class="pa-1">전체보기</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState } from 'vuex';

export default {
  components: {},
  props: {},
  data() {
    return {
      title: '연어 샐러드',
      hashtag: ['연어', '샐러드', '간편', '채식'],
      rating: 4.5,
      allergy: true,
      windowTop: 0,
    };
  },
  computed: {
    ...mapState({
      recipeNewList: (state) => state.recipe.recipeNewList,
    }),
    thumbnailBoxHeight: function() {
      return this.$refs.thumbnailBox.clientHeight;
    },
    recipeInfo: function() {
      const ret = this.recipeNewList.filter((v) => {
        return v['recipe-id'] == this.$route.params.id;
      });
      return ret[0];
    },
    recipeIngredient: function() {
      const tmp = [];
      const parse = this.parseString(this.recipeInfo['recipe-ingredient']);
      for (let i = 0; i < parse.length; i += 2) {
        if (!parse[i]) continue;
        tmp.push({ name: parse[i], quantity: parse[i + 1] });
      }
      return tmp;
    },
    recipeContext: function() {
      return this.parseString(this.recipeInfo['recipe-context']);
    },
  },
  watch: {},
  methods: {
    parseString(str) {
      return str.split('####');
    },
    onScroll() {
      this.windowTop = window.top.scrollY;
      if (this.windowTop >= this.thumbnailBoxHeight) {
        console.log('%cRecipeDetail.vue line:142', 'color: #007acc;');
      }
    },
  },
  mounted() {
    window.addEventListener('scroll', this.onScroll);
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.onScroll);
  },
};
</script>

<style scoped></style>
