<template>
  <div class="grey lighten-4">
    <Header />
    <router-view></router-view>
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
};
</script>

<style scoped></style>
