<template>
  <v-container fluid>
    <v-row>
      <v-col>
        <v-col class="d-flex flex-column align-center justify-center">
          <v-img
            min-height="200"
            max-height="200"
            min-width="200"
            max-width="200"
            src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"
            class="rounded-lg"
          >
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
    <v-row>
      <v-col class="d-flex justify-space-between align-center">
        <span class="text-h6 font-weight-black">Ingredient</span>
        <v-btn text x-small class="pa-1">전체보기</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="d-flex justify-space-between align-center">
        <span class="text-h6 font-weight-black">Recipe</span>
        <v-btn text x-small class="pa-1">전체보기</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="d-flex justify-space-between align-center">
        <span class="text-h6 font-weight-black">Review</span>
        <v-btn text x-small class="pa-1">전체보기</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="d-flex justify-space-between align-center">
        <span class="text-h6 font-weight-black">Recommand</span>
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
      rating: 3.5,
    };
  },
  computed: {
    ...mapState({
      recipeNewList: (state) => state.recipe.recipeNewList,
    }),
    recipeInfo: function() {
      const ret = this.recipeNewList.filter((v) => {
        return v['recipe-id'] == this.$route.params.id;
      });
      return ret[0];
    },
  },
  watch: {},
  methods: {},
  mounted() {},
};
</script>

<style scoped></style>
