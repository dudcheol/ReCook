<template>
  <div>
    <WatchCardItem
      v-for="(item, index) in datas"
      :key="'watchlist' + index + '/' + item.videoId"
      :src="item.videoThumbnail"
      :title="item.videoTitle"
      :username="item.videoChannel"
      :usersrc="item.videoChannelImg"
      class="white mb-4"
      @click="$router.push({ path: `/watch/${item.videoId}` })"
    />
  </div>
</template>

<script>
import WatchCardItem from './WatchCardItem.vue';
export default {
  components: { WatchCardItem },
  props: {
    datas: Array,
  },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
