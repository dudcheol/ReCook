<template>
  <v-container fluid>
    <v-row class="header-profile white pt-14">
      <v-col cols="12">
        <ProfileSimpleItem :username="user.userName || ''" :src="user.userImage || ''" />
      </v-col>
      <v-col
        cols="12"
        class="pa-1 grey lighten-4 text-center caption-1 white--text font-weight-medium"
      >
      </v-col>
    </v-row>
    <v-row class="pt-14 pb-4 mt-14" no-gutters>
      <v-col>
        <WatchCardItem
          v-for="item in likeRecipeList"
          :key="'likelist' + item.recipeId"
          :src="item.recipeMainImage"
          :title="item.recipeTitle"
          :username="item.recipeTime"
          :usersrc="'mdi-information-outline'"
          @click="$router.push(`/recipe/${item.recipeId}`)"
        />
      </v-col>
    </v-row>
    <infinite-loading @infinite="infiniteHandler">
      <div slot="spinner">
        <v-skeleton-loader class="mx-auto px-4" max-width="100%" type="card"></v-skeleton-loader>
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
import WatchCardItem from '@/components/WatchCardItem';
import { getLikeListByUserId } from '@/api/user';
import MessageEmpty from '@/components/common/MessageEmpty.vue';
export default {
  components: {
    ProfileSimpleItem,
    WatchCardItem,
    MessageEmpty,
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
          const data = response.data;

          console.log('%cUserLike.vue line:53 response.data', 'color: #007acc;', response.data);

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
