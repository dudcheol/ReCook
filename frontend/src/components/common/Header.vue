<template>
  <component :is="header"></component>
</template>

<script>
import { mapState } from 'vuex';
import HeaderMain from './HeaderMain';
import HeaderFeed from './HeaderFeed';
import HeaderMypage from './HeaderMypage';
import HeaderWatch from './HeaderWatch';
import HeaderBack from './HeaderBack';
import HeaderSearch from './HeaderSearch';

export default {
  components: {},
  props: {},
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
        this.header = this.getHeader(value);
      },
    },
  },
  methods: {
    getHeader(value) {
      const split = value.split('#');
      switch (split[0]) {
        case 'Main':
          if (split[1] === 'Search') return HeaderSearch;
          return HeaderMain;
        case 'Feed':
          if (split[1]) return HeaderBack;
          return HeaderFeed;
        case 'Watch':
          return HeaderWatch;
        case 'Mypage':
          return HeaderMypage;
        default:
          return HeaderMain;
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
