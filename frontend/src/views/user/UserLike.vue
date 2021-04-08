<template>
  <v-container fluid>
    <v-row class="header-profile white pt-14">
      <v-col cols="12">
        <ProfileSimpleItem :username="user.userName || ''" :src="user.userImage || ''" />
      </v-col>
    </v-row>
    <v-row class="py-13 mt-14" no-gutters v-if="likeRecipeList.length">
      <v-col>
        <div>
          <RecipeRecommCardList :datas="likeRecipeList" />
        </div>
      </v-col>
    </v-row>
    <v-row class="fill-height" style="height:100vh" v-else>
      <v-col class="d-flex align-center justify-center">
        <message-empty :width="200" :text="'찜한 레시피가 없어요'"></message-empty>
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
      <div slot="no-results"></div>
      <div slot="no-more"></div>
      <div slot="error"></div>
    </infinite-loading>
  </v-container>
</template>
<script>
import ProfileSimpleItem from '@/components/ProfileSimpleItem.vue';
import { getLikeListByUserId } from '@/api/user';
import MessageEmpty from '@/components/common/MessageEmpty.vue';
import LoadingCheers from '@/components/common/LoadingCheers.vue';
import RecipeRecommCardList from '@/components/RecipeRecommCardList.vue';
export default {
  name: 'UserLike',
  components: {
    ProfileSimpleItem,
    MessageEmpty,
    LoadingCheers,
    RecipeRecommCardList,
  },
  props: {
    user: Object,
  },
  data() {
    return {
      page: 0,
      size: 10,
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
          if (data.length) {
            this.page += 1;
            this.likeRecipeList.push(...data);
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
  created() {},
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
