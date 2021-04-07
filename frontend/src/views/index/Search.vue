<template>
  <div class="my-14 pa-4">
    <RecipeRecommCardList :datas="list" />
    <div v-if="!$store.state.user.searchTitle" class="grey--text caption-1">
      <v-container>
        <v-row no-gutters>
          <v-col cols="12" class="d-flex align-center sub-title-2 mb-2">
            <v-icon size="16" color="grey lighten-1">mdi-information</v-icon>검색어 작성 팁
          </v-col>
          <v-col cols="12">
            파스타, 스프, 치킨, 볶음밥 등 레시피 이름으로 검색해주세요<br />이미 알고있는 레시피
            이름은 그대로 입력하세요
          </v-col>
        </v-row>
      </v-container>
    </div>
    <infinite-loading ref="InfiniteLoading" @infinite="infiniteHandler">
      <div slot="spinner" class="py-10">
        <LoadingCheers :width="128" :height="128" class="mx-auto white rounded-circle" />
      </div>
      <div slot="no-results">
        <MessageNoResult
          v-show="!list"
          :height="200"
          :width="200"
          :text="'검색 결과를 찾지 못했어요'"
          style="width:100%"
          class="mb-4"
        />
      </div>
      <div slot="no-more"></div>
      <div slot="error"></div>
    </infinite-loading>
  </div>
</template>
<script>
import MessageNoResult from '@/components/common/MessageNoResult.vue';
import LoadingCheers from '@/components/common/LoadingCheers.vue';
import RecipeRecommCardList from '@/components/RecipeRecommCardList.vue';
import { mapActions } from 'vuex';
import { getRecipeByTitle } from '@/api/recipe';
export default {
  name: 'Search',
  components: { RecipeRecommCardList, LoadingCheers, MessageNoResult },
  props: {},
  data() {
    return {
      page: 0,
      size: 10,
      list: [],
    };
  },
  watch: {
    '$store.state.user.searchTitle': {
      immediate: true,
      handler(value) {
        this.page = 0;
        this.list = [];
        this.title = value;
        this.$refs.InfiniteLoading.stateChanger.reset();
      },
    },
  },
  methods: {
    ...mapActions(['GET_RECIPE_BY_TITLE']),
    infiniteHandler($state) {
      getRecipeByTitle(
        this.title,
        this.page,
        this.size,
        (response) => {
          const data = response.data.content;

          console.log('%cSearch.vue line:49 data', 'color: #007acc;', data);

          if (data.length) {
            this.page += 1;
            this.list.push(...data);
            $state.loaded();
          } else {
            $state.complete();
          }
        },
        () => {
          $state.error();
        }
      );
    },
  },
  deactivated() {
    console.log('%cSearch.vue line:68', 'color: #007acc;');
    this.page = 0;
    this.list = [];
    this.$store.commit('setSearchTitle', '');
  },
};
</script>
<style scoped></style>
