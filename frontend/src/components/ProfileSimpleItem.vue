<template>
  <div class="d-flex align-center" @click="$emit('click')">
    <div>
      <v-avatar :size="iconSize || 36">
        <v-img :src="src" />
      </v-avatar>
      <span class="font-weight-medium pl-3" :style="{ fontSize: (fontSize || 18) + 'px' }">{{
        username
      }}</span>
    </div>
    <div
      v-if="$store.state.user.user.userName === username"
      class="d-flex align-center justify-center ml-auto"
    >
      <v-btn text x-small class="px-1 text-caption">
        프로필 수정
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props: { username: String, src: String, iconSize: Number, fontSize: Number },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
