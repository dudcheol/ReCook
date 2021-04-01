<template>
  <v-container fluid>
    <v-row class="header-profile white pt-16">
      <v-col>
        <profile-simple-item
          :username="user.userName || ''"
          :src="user.userImage || ''"
          class="px-3"
        ></profile-simple-item>
      </v-col>
    </v-row>
    <v-row class="pt-14 pb-4" no-gutters>
      <v-col>
        <infinite-loading @infinite="infiniteHandler"></infinite-loading>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import ProfileSimpleItem from '@/components/ProfileSimpleItem.vue';
export default {
  components: {
    ProfileSimpleItem,
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
