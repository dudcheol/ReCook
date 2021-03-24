<template>
  <GridLayout
    ref="ig"
    :options="{
      align: 'center',
      transitionDuration: 0.2,
      isOverflowScroll: false,
    }"
    :layoutOptions="{
      margin: 10,
      align: 'center',
    }"
    @append="onAppend"
    @layout-complete="onLayoutComplete"
    @image-error="onImageError"
  >
    <!-- Loading element via named slot -->
    <div slot="loading">Loading...</div>
    <div class="review" v-for="(item, i) in list" :key="item.key" @click="clickItem(i)">
      <div class="thumbnail">
        <img
          :src="`https://naver.github.io/egjs-infinitegrid/assets/image/${(item.num + 1) % 59}.jpg`"
        />
      </div>
      <div class="text-subtitle-2 text-center font-weight-bold">레시피 리뷰 {{ item.num }}</div>
      <div class="text-caption text-center">작성자</div>
    </div>
  </GridLayout>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      start: 0,
      loading: false,
      list: [],
    };
  },
  computed: {},
  watch: {},
  methods: {
    loadItems(groupKey, num) {
      const items = [];
      const start = this.start || 0;

      for (let i = 0; i < num; ++i) {
        items.push({
          groupKey,
          num: start + i,
          key: start + i,
        });
      }
      this.start = start + num;
      return items;
    },
    clickItem(index) {
      console.log('%cFeed.vue line:60 index', 'color: #007acc;', index);
      // this.list.splice(index, 1);
    },
    onAppend({ groupKey, startLoading }) {
      const list = this.list;
      const items = this.loadItems(parseFloat(groupKey || 0) + 1, 5);

      startLoading();
      this.list = list.concat(items);
    },
    onLayoutComplete({ isLayout, endLoading }) {
      if (!isLayout) {
        endLoading();
      }
    },
    onImageError({ totalIndex }) {
      this.list.splice(totalIndex, 1);
    },
  },
  mounted() {},
};
</script>

<style scoped>
.review {
  width: 172px;
  opacity: 1;
}
.review .thumbnail {
  max-height: 500px;
  overflow: hidden;
  /* border-radius: 8px; */
}
.review .thumbnail img {
  width: 100%;
  border-radius: 10px;
  line-height: 0 !important;
}
.review.animate {
  transition: opacity ease 1s;
  transition-delay: 0.2s;
  opacity: 1;
}
.loading {
  position: absolute;
  width: 100%;
  height: 50px;
  line-height: 50px;
  text-align: center;
  font-weight: bold;
}
</style>
