<template>
  <div
    class="d-flex align-center"
    @click="$store.state.user.user.userName === username ? '' : $emit('click')"
  >
    <div>
      <v-avatar :size="iconSize || 36">
        <v-img v-if="!icon" :src="src" /> <v-icon v-else>{{ src }}</v-icon>
      </v-avatar>
      <span class="font-weight-medium pl-3" :style="{ fontSize: (fontSize || 18) + 'px' }">{{
        username
      }}</span>
    </div>
    <v-avatar
      v-if="$store.state.user.user.userName === username"
      class="d-flex align-center justify-center ml-auto rounded-circle dahong white--text caption pa-1 font-weight-medium"
      size="24"
    >
      ME
    </v-avatar>
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
