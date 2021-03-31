<template>
  <router-view :user="userInfo"></router-view>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {
  components: {},
  props: {},
  data() {
    return {};
  },
  computed: {
    ...mapState({
      userInfo: (state) => state.user.selectedUserInfo,
    }),
  },
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        this.GET_USERINFO_BY_NAME(value.params['user_name']);
      },
    },
  },
  methods: {
    ...mapActions(['GET_USERINFO_BY_NAME']),
  },
};
</script>

<style scoped></style>
