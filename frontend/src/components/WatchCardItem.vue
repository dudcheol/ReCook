<template>
  <div class="pa-4">
    <v-img
      min-height="200"
      max-height="200"
      src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"
      class="rounded-lg"
    >
    </v-img>
    <div class="px-1">
      <div class="text-subtitle-1 font-weight-medium pt-2">{{ title }}</div>
      <ProfileSimpleItem :username="'test'" :iconSize="24" :fontSize="14" />
    </div>
  </div>
</template>

<script>
import ProfileSimpleItem from './ProfileSimpleItem.vue';
export default {
  components: { ProfileSimpleItem },
  data() {
    return {
      title: '영상 제목',
    };
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
