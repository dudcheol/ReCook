<template>
  <div class="d-flex pl-4" style="overflow:auto">
    <RecipeCardItem v-for="item in 5" :key="'listime-' + item" />
  </div>
</template>

<script>
import RecipeCardItem from '@/components/RecipeCardItem';

export default {
  components: {
    RecipeCardItem,
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
