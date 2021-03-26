<template>
  <div class="d-flex pl-4" style="overflow:auto">
    <RecipeCardItem v-for="item in datas" :key="item['recipe-id']" :data="item" />
  </div>
</template>

<script>
import RecipeCardItem from '@/components/RecipeCardItem';

export default {
  components: {
    RecipeCardItem,
  },
  props: {
    datas: Array,
  },
  data() {
    return {};
  },
  computed: {},
  watch: {
    // datas:{
    //   immediate:true,
    //   handler(val){
    //   }
    // }
  },
  methods: {},
};
</script>

<style scoped></style>
