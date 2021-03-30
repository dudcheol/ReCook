<template>
  <component :is="header"></component>
</template>

<script>
import { mapState } from 'vuex';

export default {
  components: {},
  props: {
    type: String,
  },
  data() {
    return {
      header: '',
    };
  },
  computed: {
    ...mapState({
      pageName: (state) => state.header.pageName,
    }),
  },
  watch: {
    pageName: {
      immediate: true,
      handler(value) {
        this.header = () => import(`./Header${value}`);
      },
    },
  },
  methods: {},
};
</script>

<style lang="scss" scoped></style>
