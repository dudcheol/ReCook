<template>
  <div class="d-flex align-center">
    <v-avatar size="36">
      <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
    </v-avatar>
    <span class="text-h6 font-weight-medium pl-3">{{ username }}</span>
  </div>
</template>

<script>
export default {
  components: {},
  props: { username: String },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
