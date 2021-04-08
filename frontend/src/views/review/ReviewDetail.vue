<template>
  <v-container class="pt-14 white">
    <v-row>
      <v-col>
        <ProfileSimpleItem
          v-if="info.review || false"
          :username="info.review.user.userName"
          :src="info.review.user.userImage"
          @click="
            $router.push({
              path: `/user/${info.review.user.userName}`,
            })
          "
        />
      </v-col>
    </v-row>
    <v-row>
      <v-col class="pa-0">
        <v-img :src="info.review ? info.review.reviewImage : ''" max-height="500" aspect-ratio="1">
        </v-img>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <RecipeSimpleItem
          :title="info.recipeTitle"
          :rating="(info.review || '').reviewRating"
          :src="info.recipeImage"
          @click="$router.push({ path: `/recipe/${info.review.recipeId}` })"
        />
      </v-col>
    </v-row>
    <v-divider class="my-3"></v-divider>
    <v-row>
      <v-col class="font-weight-regular">
        {{ info.review ? info.review.reviewContext : '' }}
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
    return {};
  },
  computed: {
    ...mapState({
      info: (state) => state.review.reviewInfo,
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
  beforeDestroy() {
    this.$store.commit('clearReviewInfo');
  },
};
</script>

<style scoped></style>
