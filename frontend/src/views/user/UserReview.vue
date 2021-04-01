<template>
  <v-container fluid>
    <v-row class="header-profile white pt-16">
      <v-col>
        <profile-simple-item
          :username="user.userName || ''"
          :src="user.userImage || ''"
          class="px-3"
        ></profile-simple-item>
      </v-col>
    </v-row>
    <v-row class="pt-16 pb-4" no-gutters>
      <v-col>
        {{ user.userIntroduce }}
      </v-col>
    </v-row>
    <v-row>
      <v-col v-for="n in 20" :key="n" class="d-flex child-flex pa-0" cols="4">
        <v-img
          :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"
          :lazy-src="`https://picsum.photos/10/6?image=${n * 5 + 10}`"
          aspect-ratio="1"
          class="grey lighten-2"
        >
          <template v-slot:placeholder>
            <v-row class="fill-height ma-0" align="center" justify="center">
              <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
            </v-row>
          </template>
        </v-img>
      </v-col>
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
