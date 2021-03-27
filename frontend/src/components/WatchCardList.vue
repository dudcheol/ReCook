<template>
  <div>
    <WatchCardItem v-for="(item, index) in 3" :key="index" />
  </div>
</template>

<script>
import WatchCardItem from './WatchCardItem.vue';
export default {
  components: { WatchCardItem },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
