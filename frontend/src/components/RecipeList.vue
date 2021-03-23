<template>
  <div class="d-flex pl-4" style="overflow:auto">
    <recipe-item v-for="item in 5" :key="'listime-' + item"> </recipe-item>
  </div>
</template>

<script>
import RecipeItem from '@/components/RecipeItem';

export default {
  components: {
    RecipeItem,
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
