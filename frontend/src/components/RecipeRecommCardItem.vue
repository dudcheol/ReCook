<template>
  <v-card class="pa-2" rounded="xl" elevation="0" @click="$emit('click')">
    <div class="d-flex flex-no-wrap">
      <v-avatar class="ma-3" size="120">
        <v-img :src="data.recipeMainImage || data['recipe-main-image']"></v-img>
      </v-avatar>
      <div class="pa-3 my-auto">
        <div class="h6 pt-2">
          {{ (data.recipeTitle || data['recipe-title']) | truncate(9, '..') }}
        </div>

        <div class="caption-1 pt-2">
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
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
