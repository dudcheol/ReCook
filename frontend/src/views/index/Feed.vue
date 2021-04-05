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
    <div slot="loading" style="width:100%">
      <v-row>
        <v-col>
          <v-skeleton-loader class="mx-auto ml-3 rounded-xl" type="image"></v-skeleton-loader>
          <div class="d-flex align-center">
            <v-skeleton-loader class="mx-auto pt-3" type="chip"></v-skeleton-loader>
          </div>
        </v-col>
        <v-col>
          <v-skeleton-loader class="mx-auto mr-3 rounded-xl" type="image"></v-skeleton-loader>
          <div class="d-flex align-center">
            <v-skeleton-loader class="mx-auto pt-3" type="chip"></v-skeleton-loader>
          </div>
        </v-col>
      </v-row>
    </div>
    <div
      class="review"
      v-for="item in list"
      :key="'reviewItem' + item.reviewId"
      @click="$router.push({ path: `review/${item.reviewId}` })"
    >
      <div class="thumbnail">
        <img :src="item.reviewImage" />
      </div>
      <div class="subtitle-2 text-center font-weight-bold">
        {{ item.reviewContext | truncate(12, '..') }}
      </div>
      <div class="caption-1 text-center">{{ item.user.userName | truncate(10, '..') }}</div>
    </div>
  </GridLayout>
</template>

<script>
import { getAllReviews } from '@/api/review';
export default {
  components: {},
  props: {},
  data() {
    return {
      pageNumber: 0,
      pageSize: 10,
      loading: false,
      list: [],
    };
  },
  computed: {},
  watch: {},
  methods: {
    onAppend({ startLoading, endLoading }) {
      getAllReviews(
        this.pageNumber,
        this.pageSize,
        (response) => {
          // console.log('%cFeed.vue line:72 response.data', 'color: #007acc;', response.data.content);
          console.log(
            '%cFeed.vue line:73 response.data.content',
            'color: #007acc;',
            response.data.content
          );
          if (response.data.content.length) {
            // const responseList = response.data.content;
            const result = response.data.content.filter((e) => e.reviewImage);
            const list = this.list;

            this.pageNumber += 1;
            startLoading();
            this.list = list.concat(result);
            console.log('%cFeed.vue line:82 this.list', 'color: #007acc;', this.list);
          } else {
            endLoading();
          }
        },
        (error) => {
          console.log('%cFeed.vue line:88 error', 'color: #007acc;', error);
        }
      );
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
