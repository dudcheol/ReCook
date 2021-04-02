<template>
  <div>
    <h1>Watch detail vue</h1>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {};
  },
  computed: {},
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        console.log(
          '%cWatchDetail.vue line:19 value.params',
          'color: #007acc;',
          value.params['video_id']
        );
      },
    },
  },
  methods: {},
};
</script>

<style scoped></style>
