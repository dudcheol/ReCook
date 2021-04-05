<template>
  <v-card class="pa-2" rounded="xl" elevation="0" @click="$emit('click')">
    <div class="d-flex flex-no-wrap">
      <v-avatar class="ma-3" size="125">
        <v-img :src="data.recipeMainImage || data['recipe-main-image']"></v-img>
      </v-avatar>
      <div class="pa-3 my-auto">
        <div class="headline pt-2">
          {{ (data.recipeTitle || data['recipe-title']) | truncate(14, '..') }}
        </div>

        <div class="text-caption pt-2">
          <v-icon small>mdi-timer</v-icon>
          <span>
            {{ (data['recipeTime'] || data['recipe-time']).split(' ')[0] }}
          </span>
          <v-icon small>mdi-account-outline</v-icon>
          <span>
            {{
              (data['recipeTime'] || data['recipe-time']).split(' ')[1].replace('인기준', '인분')
            }}
          </span>
        </div>
        <!-- <v-chip-group active-class="none">
          <v-chip
            color="grey lighten-3"
            text-color="grey darken-2"
            v-for="(item, index) in ingredients"
            :key="index"
            small
            class="px-2 mr-1 mb-1"
          >
            {{ item }}
          </v-chip>
        </v-chip-group> -->
      </div>
    </div>
  </v-card>
</template>

<script>
export default {
  components: {},
  props: {
    data: Object,
  },
  data() {
    return {};
  },
  computed: {
    // ingredients() {
    //   const ret = (this.data.recipeIngredient || this.data['recipe-ingredient']).split('####');
    //   ret.splice(ret.length - 1, 1);
    //   return ret;
    // },
  },
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
