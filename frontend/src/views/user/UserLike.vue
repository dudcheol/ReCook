<template>
  <v-container fluid>
    <v-row class="header-profile white pt-16">
      <v-col>
        <profile-simple-item
          :username="'test'"
          :src="'https://picsum.photos/500/300?image=1'"
          class="px-3"
        ></profile-simple-item>
      </v-col>
    </v-row>
    <v-row class="pt-14 pb-4" no-gutters>
      <v-col> </v-col>
    </v-row>
  </v-container>
</template>
<script>
import ProfileSimpleItem from '@/components/ProfileSimpleItem.vue';
export default {
  components: {
    ProfileSimpleItem,
  },
  props: {
    user: Object,
  },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>
<style scoped>
.header-profile {
  z-index: 3;
  position: fixed;
  top: 0px;
  width: 100%;
}
</style>
