<template>
  <div class="d-flex pl-4" style="overflow:auto">
    <RecipeCardItem v-for="item in datas" :key="item['recipe-id']" :data="item" />
    <div v-if="datas.length">
      <v-card
        rounded="xl"
        width="120"
        height="230"
        class="mr-4 pa-5"
        outlined
        @click="$router.push(`/recipe/list/${type}`)"
      >
        <div>더 많은</div>
        <div class="font-weight-black">레시피</div>
        <div>보기</div>
        <div><v-icon>mdi-arrow-right</v-icon></div>
      </v-card>
    </div>
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
    type: String,
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
