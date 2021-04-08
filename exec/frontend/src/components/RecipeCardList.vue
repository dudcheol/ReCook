<template>
  <div class="d-flex pl-4" style="overflow:auto">
    <RecipeCardItem
      v-for="(item, index) in datas"
      :key="index + 'recipe' + item['recipe-id']"
      :data="item"
      @click="
        $router.push({
          path: `/recipe/${item['recipe-id']}`,
        });
        $emit('click');
      "
      :elevation="elevation"
    />
    <div v-if="datas.length && !total">
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
    total: Boolean,
    elevation: Boolean,
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
