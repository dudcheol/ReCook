<template>
  <v-container fluid>
    <v-row class="header-profile white pt-14">
      <v-col cols="12">
        <ProfileSimpleItem :username="user.userName || ''" :src="user.userImage || ''" />
      </v-col>
    </v-row>
    <v-row class="py-13 my-14" no-gutters>
      <v-col>
        <!-- <WatchCardItem
          v-for="item in likeRecipeList"
          :key="'likelist' + item.recipeId"
          :src="item.recipeMainImage"
          :title="item.recipeTitle"
          :username="item.recipeTime"
          :usersrc="'mdi-information-outline'"
          @click="$router.push(`/recipe/${item.recipeId}`)"
        /> -->
        <recipe-recomm-card-item
          v-for="item in likeRecipeList"
          :key="'likelist' + item.recipeId"
          :data="item"
          @click="$router.push(`/recipe/${item.recipeId}`)"
          class="mb-4"
        ></recipe-recomm-card-item>
      </v-col>
    </v-row>
    <infinite-loading ref="InfiniteLoading" @infinite="infiniteHandler">
      <div slot="spinner" class="">
        <loading-cheers
          :width="150"
          :height="150"
          class="white rounded-circle mx-auto"
        ></loading-cheers>
      </div>
      <div slot="no-results">
        <message-empty :width="200" :text="'찜한 레시피가 없어요'"></message-empty>
      </div>
      <div slot="no-more"></div>
      <div slot="error"></div>
    </infinite-loading>
  </v-container>
</template>
<script>
import ProfileSimpleItem from '@/components/ProfileSimpleItem.vue';
// import WatchCardItem from '@/components/WatchCardItem';
import { getLikeListByUserId } from '@/api/user';
import MessageEmpty from '@/components/common/MessageEmpty.vue';
import RecipeRecommCardItem from '@/components/RecipeRecommCardItem.vue';
import LoadingCheers from '@/components/common/LoadingCheers.vue';
export default {
  components: {
    ProfileSimpleItem,
    // WatchCardItem,
    MessageEmpty,
    RecipeRecommCardItem,
    LoadingCheers,
  },
  props: {
    user: Object,
  },
  data() {
    return {
      page: 0,
      size: 5,
      likeRecipeList: [],
    };
  },
  computed: {},
  watch: {},
  methods: {
    infiniteHandler($state) {
      getLikeListByUserId(
        this.$store.state.user.user.userId,
        this.page,
        this.size,
        (response) => {
          console.log('%cUserLike.vue line:72 response.data', 'color: #007acc;', response.data);
          const data = response.data;

          if (data.length) {
            this.page += 1;
            this.likeRecipeList.push(...data);
            $state.loaded();
          } else {
            $state.complete();
          }
        },
        (error) => {
          console.log('%cUserLike.vue line:61 error', 'color: #007acc;', error);
        }
      );
    },
  },
  created() {
    // console.log('%cUserLike.vue line:107 ', 'color: #007acc;');
    // if (this.$refs.InfiniteLoading) {
    //   this.$refs.InfiniteLoading.stateChanger.reset();
    // }
  },
  mounted() {},
};
</script>
<style scoped>
.header-profile {
  z-index: 3;
  position: fixed;
  top: 0px;
  width: 100%;
}
</style>
