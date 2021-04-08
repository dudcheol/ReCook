<template>
  <div class="pa-4" @click="$emit('click')">
    <v-img min-height="200" max-height="200" :src="src || ''" class="rounded-lg"> </v-img>
    <div class="px-1">
      <div class="sub-title-2 font-weight-regular pt-2">{{ title }}</div>
      <ProfileSimpleItem
        :username="username || ''"
        :src="usersrc || 'mdi-account'"
        :iconSize="24"
        :fontSize="12"
        class="pt-1"
      />
    </div>
  </div>
</template>

<script>
import ProfileSimpleItem from './ProfileSimpleItem.vue';
export default {
  components: { ProfileSimpleItem },
  props: {
    src: String,
    title: String,
    username: String,
    usersrc: String,
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
