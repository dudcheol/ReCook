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
      v-if="content"
      class="pa-4 grey lighten-4 rounded-xl ma-5"
      style="position:fixed; bottom:0; width:80%;"
      @click="more = !more"
    >
      <span v-if="more" class="black--text">{{ content }}</span>
      <span
        v-else
        class="black--text"
        v-html="$options.filters.truncate(content, 60, moreBtn)"
      ></span>
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
      more: false,
      windowHeight: 0,
      moreBtn:
        '...<span class="font-weight-bold sub-title-2 grey--text text--darken-1">더 보기</span>',
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
