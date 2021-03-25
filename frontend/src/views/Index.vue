<template>
  <div>
    <Header />
    <router-view class="pb-16 my-14"></router-view>
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
