<template>
  <router-view :user="$store.state.user.user"></router-view>
</template>

<script>
export default {
  name: 'Mypage',
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
        if (value.name === 'Mypage')
          this.$router
            .replace({
              path: `/${this.$store.state.user.user.userName}/${this.$store.state.user.mypageTabState}`,
            })
            .catch(() => {});
      },
    },
  },
  methods: {},
};
</script>

<style scoped></style>
