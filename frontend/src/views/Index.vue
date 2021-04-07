<template>
  <div class="grey lighten-4">
    <Header />
    <keep-alive include="Watch,UserLike,Feed">
      <router-view></router-view>
    </keep-alive>
    <Footer />
  </div>
</template>

<script>
import Header from '@/components/common/Header';
import Footer from '@/components/common/Footer';

export default {
  components: {
    Header,
    Footer,
  },
  props: {},
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
  created() {},
};
</script>

<style scoped></style>
