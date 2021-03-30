<template>
  <div :style="{ minHeight: windowHeight + 'px' }" class="d-flex flex-column">
    <v-img :src="imageUrl" contain>
      <template v-slot:placeholder>
        <v-row class="fill-height ma-0" align="center" justify="center">
          <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
        </v-row>
      </template>
    </v-img>
    <div class="pa-3">
      {{ content }}
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
<style lang=""></style>
