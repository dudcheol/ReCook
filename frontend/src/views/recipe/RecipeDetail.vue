<template>
  <div>
    <h4>{{ testInfo[0]['recipe-title'] }}</h4>
    <h6>{{ testInfo[0]['recipe-ingredient'] }}</h6>
    <h4>{{ testInfo[0]['recipe-context'] }}</h4>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  components: {},
  props: {},
  data() {
    return {};
  },
  computed: {
    ...mapState({
      recipeNewList: (state) => state.recipe.recipeNewList,
    }),
    testInfo: function() {
      return this.recipeNewList.filter((v) => {
        return v['recipe-id'] == this.$route.params.id;
      });
    },
  },
  watch: {},
  methods: {},
  mounted() {},
};
</script>

<style scoped></style>
