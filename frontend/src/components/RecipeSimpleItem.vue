<template>
  <div class="d-flex align-center">
    <v-avatar rounded="lg" size="56">
      <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
    </v-avatar>
    <div class="d-flex flex-column align-self-center pl-3">
      <span class="text-subtitle-1 font-weight-medium pa-1 mt-3" style="line-height:0">{{
        title
      }}</span>
      <v-rating
        :value="rating"
        empty-icon="mdi-star-outline"
        full-icon="mdi-star"
        half-icon="mdi-star-half"
        background-color="grey lighten-1"
        color="yellow accent-4"
        dense
        half-increments
        small
        readonly
        length="5"
        class="pt-1"
      ></v-rating>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props: { title: String, rating: Number },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
