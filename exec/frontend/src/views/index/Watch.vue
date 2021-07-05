<template>
  <div class="pb-10 my-14">
    <WatchCardList :datas="list" />
    <infinite-loading @infinite="infiniteHandler">
      <div slot="spinner">
        <v-skeleton-loader class="mx-auto px-4" max-width="100%" type="card"></v-skeleton-loader>
      </div>
      <div slot="no-results"></div>
      <div slot="no-more"></div>
      <div slot="error"></div>
    </infinite-loading>
  </div>
</template>

<script>
import WatchCardList from '@/components/WatchCardList.vue';
import { getVideoList } from '@/api/video';
export default {
  name: 'Watch',
  components: { WatchCardList },
  data() {
    return {
      page: 0,
      size: 10,
      list: [],
    };
  },
  computed: {},
  watch: {},
  methods: {
    infiniteHandler($state) {
      getVideoList(
        this.page,
        this.size,
        (response) => {
          const data = response.data.content;
          if (data.length) {
            this.page += 1;
            this.list.push(...data);
            $state.loaded();
          } else {
            $state.complete();
          }
        },
        () => {}
      );
    },
  },
};
</script>

<style scoped></style>
