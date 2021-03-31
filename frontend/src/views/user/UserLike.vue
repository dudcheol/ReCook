<template>
  <v-container fluid>
    <v-row class="header-profile white pt-14">
      <v-col cols="12">
        <profile-simple-item
          :username="user.userName || ''"
          :src="user.userImage || ''"
        ></profile-simple-item>
      </v-col>
      <!-- <v-col cols="12" class="pa-0 dahong text-center text-caption white--text font-weight-medium">
        {{ user.userName }}님이 <strong>좋아요</strong>한 <strong>레시피</strong>입니다
      </v-col> -->
    </v-row>
    <v-row class="pt-14 pb-4" no-gutters>
      <v-col>
        <watch-card-list></watch-card-list>
      </v-col>
    </v-row>
    <infinite-loading @infinite="infiniteHandler">
      <div slot="spinner">
        <v-skeleton-loader class="mx-auto px-4" max-width="100%" type="card"></v-skeleton-loader>
      </div>
    </infinite-loading>
  </v-container>
</template>
<script>
import ProfileSimpleItem from '@/components/ProfileSimpleItem.vue';
import WatchCardList from '@/components/WatchCardList.vue';
export default {
  components: {
    ProfileSimpleItem,
    WatchCardList,
  },
  props: {
    user: Object,
  },
  data() {
    return {
      page: 1,
      list: [],
    };
  },
  computed: {},
  watch: {},
  methods: {
    infiniteHandler($state) {
      console.log('%cUserLike.vue line:38 $state', 'color: #007acc;', $state);
      // axios
      //   .get(api, {
      //     params: {
      //       page: this.page,
      //     },
      //   })
      //   .then(({ data }) => {
      //     if (data.hits.length) {
      //       this.page += 1;
      //       this.list.push(...data.hits);
      //       $state.loaded();
      //     } else {
      //       $state.complete();
      //     }
      //   });
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
