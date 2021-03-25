<template>
  <div class="pb-2">
    <v-card
      rounded="xl"
      width="150"
      height="230"
      class="text-center mr-4"
      outlined
      @click="
        $router.push({
          name: 'RecipeDetail',
          params: { type: 'RecipeDetail', id: data['recipe-id'] },
        })
      "
    >
      <v-img :src="data['recipe-main-image']" max-height="150" min-height="150" />
      <v-container class="mb-3">
        <v-row>
          <v-col>
            <span class="font-weight-bold">
              {{ data['recipe-title'] | truncate(8, '..') }}
            </span>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="text-caption pa-0">
            <v-icon small>mdi-timer</v-icon>
            <span>
              {{ data['recipe-time'] }}
            </span>
            <v-icon small>mdi-account-outline</v-icon>
            <span>
              {{ data['recipe-person'].replace('인기준', '인분') }}
            </span>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<script>
export default {
  components: {},
  filters: {
    truncate: function(text, length, suffix) {
      if (text.length > length) {
        return text.substring(0, length) + suffix;
      } else {
        return text;
      }
    },
  },
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
