<template>
  <div :style="{ minHeight: windowHeight + 'px' }" class="d-flex flex-column">
    <v-img :src="imageUrl" contain></v-img>
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
    console.log(
      '%cRecipeSlideItem.vue line:26 this.windowHeight',
      'color: #007acc;',
      this.windowHeight
    );
  },
};
</script>
<style lang=""></style>
