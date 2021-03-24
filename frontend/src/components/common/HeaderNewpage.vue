<template>
  <v-app-bar flat color="white">
    <v-row>
      <v-col cols="2" class="pa-0">
        <v-btn icon @click="$router.go(-1)">
          <v-icon>mdi-{{ left }}</v-icon>
        </v-btn>
      </v-col>
      <v-col class="d-flex justify-space-around">
        <span class="font-weight-medium text-subtitle-1">{{ center }}</span>
      </v-col>
      <v-col cols="2" class="d-flex justify-end">
        <v-icon>mdi-{{ right }}</v-icon>
      </v-col>
    </v-row>
  </v-app-bar>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      left: '',
      center: '',
      right: '',
    };
  },
  computed: {},
  watch: {
    '$route.params.type': {
      immediate: true,
      handler(type) {
        switch (type) {
          case 'fridge-ingredient':
            this.left = 'close';
            this.center = '남은 재료 선택';
            this.right = '';
            break;
          case 'fridge-recomm':
            this.left = 'close';
            this.center = '남은 재료로 추천받기';
            this.right = '';
            break;
          case 'recipe-detail':
            this.left = 'chevron-left';
            this.center = '';
            this.right = 'heart';
            break;
          case 'review-detail':
            this.left = 'close';
            this.center = '';
            this.right = '';
            break;
          case 'profile-edit':
            this.left = 'close';
            this.center = '프로필 수정';
            this.right = '';
        }
      },
    },
  },
  methods: {},
};
</script>

<style lang="scss" scoped></style>
