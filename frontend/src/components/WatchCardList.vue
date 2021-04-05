<template>
  <div>
    <WatchCardItem
      v-for="item in datas"
      :key="'likelist' + item.recipeId"
      :src="item.recipeMainImage"
      :title="item.recipeTitle"
      :username="item.recipeTime"
      :usersrc="'mdi-information-outline'"
    />
  </div>
</template>

<script>
import WatchCardItem from './WatchCardItem.vue';
export default {
  components: { WatchCardItem },
  props: {
    datas: Array,
  },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
