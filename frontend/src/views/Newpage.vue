<template>
  <div>
    <Header />
    <router-view class="mt-14"></router-view>
  </div>
</template>

<script>
import Header from '@/components/common/HeaderNewpage';
export default {
  components: {
    Header,
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
