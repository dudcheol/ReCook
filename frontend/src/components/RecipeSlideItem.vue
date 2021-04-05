<template>
  <div
    :style="{ minHeight: windowHeight + 'px' }"
    class="d-flex flex-column align-center justify-center"
  >
    <v-img :src="imageUrl" max-width="400" contain class="pb-16 mb-16">
      <template v-slot:placeholder>
        <v-row class="fill-height ma-0" align="center" justify="center">
          <v-progress-circular indeterminate color="grey lighten-1"></v-progress-circular>
        </v-row>
      </template>
    </v-img>
    <div
      class="pa-4 grey lighten-4 rounded-xl ma-5"
      style="position:fixed; bottom:0; width:80%; height:80px; overflow:auto"
    >
      <span class="black--text">{{ content }}</span>
    </div>
  </div>
</template>
<script>
export default {
  components: {},
  props: {
    imageUrl: String,
    content: String,
  },
  data() {
    return {
      windowHeight: 0,
    };
  },
  computed: {},
  watch: {},
  methods: {},
  mounted() {
    this.windowHeight = window.innerHeight - 56;
  },
};
</script>
<style scoped></style>
