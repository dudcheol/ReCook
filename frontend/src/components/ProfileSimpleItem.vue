<template>
  <div class="d-flex align-center">
    <v-avatar :size="iconSize || 36">
      <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
    </v-avatar>
    <span class="font-weight-medium pl-3" :style="{ fontSize: (fontSize || 18) + 'px' }">{{
      username
    }}</span>
  </div>
</template>

<script>
export default {
  components: {},
  props: { username: String, iconSize: Number, fontSize: Number },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
