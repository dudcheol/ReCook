<template>
  <div class="d-flex align-center" @click="$emit('click')">
    <v-avatar rounded="lg" size="56">
      <v-img :src="src">
        <template v-slot:placeholder>
          <v-row class="fill-height ma-0" align="center" justify="center">
            <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
          </v-row>
        </template>
      </v-img>
    </v-avatar>
    <div class="d-flex flex-column align-self-center pl-3">
      <span class="subtitle-1 font-weight-medium pa-1 mt-3" style="line-height:0">{{ title }}</span>
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
  props: { title: String, rating: Number, src: String },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
