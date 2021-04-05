<template>
  <div class="d-flex align-center" @click="$emit('click')">
    <div>
      <v-avatar :size="iconSize || 36">
        <v-img v-if="!icon" :src="src" /> <v-icon v-else>{{ src }}</v-icon>
      </v-avatar>
      <span class="font-weight-medium pl-3" :style="{ fontSize: (fontSize || 18) + 'px' }">{{
        username
      }}</span>
    </div>
    <div
      v-if="$store.state.user.user.userName === username"
      class="d-flex align-center justify-center ml-auto"
    >
      <v-btn text x-small class="px-1 caption-1">
        프로필 수정
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props: { username: String, src: String, iconSize: Number, fontSize: Number, icon: Boolean },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
