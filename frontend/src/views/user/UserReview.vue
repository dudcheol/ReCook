<template>
  <v-container fluid fill-height>
    <v-row class="header-profile white pt-14">
      <v-col>
        <profile-simple-item
          :username="selectedUser.userName || ''"
          :src="selectedUser.userImage || ''"
        ></profile-simple-item>
      </v-col>
    </v-row>
    <v-row class="fill-height grey lighten-4" v-if="reviewList.length">
      <v-col cols="12">
        <v-row class="mt-16 white">
          <v-col>
            <span class="h6 font-weight-black">작성한 리뷰</span>
          </v-col>
        </v-row>
        <v-row>
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
                <v-row class="ma-0" align="center" justify="center">
                  <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                </v-row>
              </template>
            </v-img>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-row v-else class="white grey lighten-4" style="height:100vh">
      <v-col class="d-flex justify-center align-center">
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
  data() {
    return {};
  },
  computed: {
    ...mapState({
      reviewList: (state) => state.review.selectedUserReviewList,
      selectedUser: (state) => state.user.selectedUserInfo,
    }),
  },
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        const userName = value.params.user_name;
        this.GET_REVIEW_LIST_BY_USERNAME(value.params.user_name);
        this.GET_USERINFO_BY_NAME(userName);
      },
    },
  },
  methods: {
    ...mapActions(['GET_REVIEW_LIST_BY_USERNAME', 'GET_USERINFO_BY_NAME']),
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
