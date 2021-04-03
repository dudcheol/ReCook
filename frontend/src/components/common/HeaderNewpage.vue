<template>
  <v-app-bar flat color="white" fixed>
    <v-row>
      <v-col cols="2" class="d-flex align-center justify-center">
        <v-btn icon @click="$router.go(-1)">
          <v-icon>mdi-{{ left }}</v-icon>
        </v-btn>
      </v-col>
      <v-col class="d-flex justify-space-around align-center">
        <span class="font-weight-medium text-subtitle-1">{{ center }}</span>
      </v-col>
      <v-col cols="2" class="d-flex align-center justify-center">
        <v-btn icon>
          <v-icon>mdi-{{ right }}</v-icon>
        </v-btn>
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
    '$router.currentRoute.name': {
      immediate: true,
      handler(value) {
        switch (value) {
          case 'FridgeIngredient':
            this.left = 'close';
            this.center = '남은 재료 선택';
            this.right = '';
            break;
          case 'FridgeRecomm':
            this.left = 'close';
            this.center = '남은 재료로 추천받기';
            this.right = '';
            break;
          case 'RecipeDetail':
            this.left = 'chevron-left';
            this.center = '';
            this.right = 'heart';
            break;
          case 'ReviewDetail':
            this.left = 'close';
            this.center = '';
            this.right = '';
            break;
          case 'ReviewWrite':
            this.left = 'close';
            this.center = '';
            this.right = '';
            break;
          case 'ProfileEdit':
            this.left = 'close';
            this.center = '프로필 수정';
            this.right = '';
            break;
          case 'Login':
            this.left = 'close';
            this.center = 'RECOOK';
            this.right = '';
            break;
          case 'WatchDetail':
            this.left = 'chevron-left';
            this.center = '';
            this.right = '';
            break;
          case 'RecipeList':
            this.left = 'chevron-left';
            this.center = `${this.convertRecipeListTitle(
              this.$route.path.split('/')[3]
            )} 레시피 전체`;
            this.right = '';
            break;
        }
      },
    },
  },
  methods: {
    convertRecipeListTitle(type) {
      switch (type) {
        case 'recommend':
          return '추천';
        case 'popular':
          return '인기';
        case 'recent':
        default:
          return '최신';
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
