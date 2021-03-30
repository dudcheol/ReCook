<template>
  <v-container>
    <v-row>
      <v-col>
        <ProfileSimpleItem
          v-if="reviewInfo.user"
          :username="reviewInfo.user.userName"
          :src="reviewInfo.user.userImage"
        />
      </v-col>
    </v-row>
    <v-row>
      <v-col class="pa-0">
        <v-img :src="reviewInfo.reviewImage" max-height="500" aspect-ratio="1"> </v-img>
      </v-col>
    </v-row>
    <v-row>
      <v-col @click="$router.push({ path: `/recipe/${reviewInfo.recipeId}` })">
        <RecipeSimpleItem
          :title="reviewInfo.recipeTitle"
          :rating="reviewInfo.recipeRating"
          :src="reviewInfo.recipeImage"
        />
      </v-col>
    </v-row>
    <v-divider class="my-3"></v-divider>
    <v-row>
      <v-col class="text-body-1 font-weight-regular">
        {{ reviewInfo.reviewContext }}
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ProfileSimpleItem from '@/components/ProfileSimpleItem';
import RecipeSimpleItem from '@/components/RecipeSimpleItem.vue';
import { mapActions, mapState } from 'vuex';

export default {
  components: { ProfileSimpleItem, RecipeSimpleItem },
  props: {},
  data() {
    return {
      content: '리뷰 테스트 화면입니다.',
    };
  },
  computed: {
    ...mapState({
      reviewInfo: (state) => state.review.reviewInfo,
    }),
  },
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        this.GET_REVIEW_INFO(value.params['review_id']);
      },
    },
  },
  methods: {
    ...mapActions(['GET_REVIEW_INFO']),
  },
};
</script>

<style scoped></style>
