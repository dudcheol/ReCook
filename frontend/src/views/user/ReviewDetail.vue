<template>
  <v-container>
    <v-row>
      <v-col>
        <ProfileSimpleItem :username="'test'" />
      </v-col>
    </v-row>
    <v-row>
      <v-col class="pa-0">
        <v-img
          src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"
          max-height="500"
          aspect-ratio="1"
        >
        </v-img>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <RecipeSimpleItem :title="'레시피 이름'" :rating="4.5" />
      </v-col>
    </v-row>
    <v-divider class="my-3"></v-divider>
    <v-row>
      <v-col class="text-body-1 font-weight-regular">
        {{ content }}
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ProfileSimpleItem from '@/components/ProfileSimpleItem';
import RecipeSimpleItem from '@/components/RecipeSimpleItem.vue';

export default {
  components: { ProfileSimpleItem, RecipeSimpleItem },
  props: {},
  data() {
    return {
      content: '리뷰 테스트 화면입니다.',
    };
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
