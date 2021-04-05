<template>
  <v-container fluid>
    <v-row class="header-profile white pt-14">
      <v-col>
        <profile-simple-item
          v-if="user || query"
          :username="(user || query).userName || ''"
          :src="(user || query).userImage || ''"
        ></profile-simple-item>
      </v-col>
    </v-row>
    <v-row class="pt-10" no-gutters>
      <v-col>
        {{ (user || query).userIntroduce }}
      </v-col>
    </v-row>
    <v-row class="pt-16">
      <v-col>
        <span class="h6 font-weight-black">리뷰</span>
      </v-col>
    </v-row>
    <v-row v-if="reviewList.length">
      <v-col
        v-for="item in reviewList"
        :key="'review' + item.reviewId"
        class="d-flex child-flex pa-0"
        cols="4"
      >
        <v-img
          v-if="item.reviewImage"
          :src="item.reviewImage"
          aspect-ratio="1"
          class="grey lighten-2"
          @click="$router.push({ path: `/review/${item.reviewId}` })"
        >
          <template v-slot:placeholder>
            <v-row class="fill-height ma-0" align="center" justify="center">
              <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
            </v-row>
          </template>
        </v-img>
      </v-col>
    </v-row>
    <v-row v-else>
      <v-col class="pa-0">
        <message-empty :width="200" :text="'작성한 리뷰가 없어요'"></message-empty>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import ProfileSimpleItem from '@/components/ProfileSimpleItem.vue';
import { mapActions, mapState } from 'vuex';
import MessageEmpty from '@/components/common/MessageEmpty.vue';
export default {
  components: {
    ProfileSimpleItem,
    MessageEmpty,
  },
  props: {
    user: Object,
  },
  data() {
    return {
      query: {},
    };
  },
  computed: {
    ...mapState({
      reviewList: (state) => state.review.selectedUserReviewList,
    }),
  },
  watch: {
    user: {
      immediate: true,
      handler(value) {
        if (value) this.GET_REVIEW_LIST_BY_USERNAME(value.userName);
      },
    },
    $route: {
      immediate: true,
      handler(value) {
        this.query = value.query.user;
        this.GET_REVIEW_LIST_BY_USERNAME(this.query.userName);
        console.log('%cUserReview.vue line:83 this.query', 'color: #007acc;', this.query);
      },
    },
  },
  methods: {
    ...mapActions(['GET_REVIEW_LIST_BY_USERNAME']),
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
