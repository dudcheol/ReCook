<template>
  <div>
    <Header />
    <router-view></router-view>
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
    return {
      left: '',
      right: '',
      center: '',
    };
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
