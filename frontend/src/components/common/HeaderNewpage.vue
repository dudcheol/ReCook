<template>
  <v-app-bar flat color="white" fixed>
    <!-- <div class="d-flex justify-space-between align-center" style="width:100%">
      <div class="d-flex align-center justify-center">
        <v-btn icon @click="$router.go(-1)">
          <v-icon>mdi-{{ left }}</v-icon>
        </v-btn>
      </div>
      <div class="d-flex justify-space-around align-center">
        <span class="font-weight-medium text-subtitle-1">{{ center }}</span>
      </div>
      <div class="d-flex align-center justify-center">
        <div v-if="right">
          <v-btn
            icon
            @click="$router.push({ path: `${$route.params.recipe_id}/write`, name: 'RecipeWrite' })"
            ><v-icon>mdi-pencil</v-icon></v-btn
          >
          <v-btn icon><v-icon>mdi-heart</v-icon></v-btn>
        </div>
      </div>
    </div> -->
    <v-row>
      <v-col cols="3" class="d-flex align-center pa-0">
        <v-btn icon @click="$router.go(-1)">
          <v-icon>mdi-{{ left }}</v-icon>
        </v-btn>
      </v-col>
      <v-col class="d-flex justify-space-around align-center">
        <span class="font-weight-medium subtitle-1">{{ center }}</span>
      </v-col>
      <v-col cols="3" class="d-flex align-center justify-end pa-0 mr-1">
        <!-- <v-btn v-for="(item, index) in right.split('#')" :key="'nheader' + index" icon>
          <v-icon>mdi-{{ item }}</v-icon>
        </v-btn> -->
        <div v-if="right" class="d-flex">
          <v-btn icon @click="$router.push({ path: `${$route.params.recipe_id}/write` })"
            ><v-icon>mdi-pencil</v-icon></v-btn
          >
          <v-btn v-if=!isLogin icon><v-icon>mdi-heart</v-icon></v-btn>
          <v-btn v-if="isLogin && likeFlag=='No'" icon @click=clickLike><v-icon>mdi-heart-outline</v-icon></v-btn>
          <v-btn v-if="isLogin && likeFlag=='Yes'" icon @click=clickLike><v-icon color='red'>mdi-heart</v-icon></v-btn>
        </div>
      </v-col>
    </v-row>
  </v-app-bar>
</template>

<script>
import {checkLike,likePush} from '../../api/Like';
export default {
  components: {},
  props: {},
  data() {
    return {
      left: '',
      center: '',
      right: '',
      isLogin:'',
      likeFlag:'',
      recipe_id:'',
      user_id:'',
    };
  },
  created(){
    if(this.isLogin){
      checkLike(this.$route.params.recipe_id,this.user_id,(response)=>{this.likeFlag=response.data},(error) => {
        console.log(error);
      });
    }
  },
  computed: {},
  watch: {
    '$store.state.user.user': {
      immediate: true,
      handler(value) {
        if (value.userId) {
          this.user_id=value.userId
          this.isLogin = true;
        } else {
          this.isLogin = false;
        }
      },
    },
    $route: {
      immediate: true,
      handler(value) {
        switch (value.name) {
          case 'FridgeIngredient':
            this.left = 'close';
            this.center = '남은 재료 선택';
            this.right = '';
            break;
          case 'FridgeRecomm':
            this.left = 'chevron-left';
            this.center = '남은 재료로 추천받기';
            this.right = '';
            break;
          case 'RecipeDetail':
            this.left = 'chevron-left';
            this.center = '';
            this.right = 'pencil#heart';
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
    clickLike(){
      likePush(this.$route.params.recipe_id,this.user_id,(response)=>{
        if(response.data=='Like'){
            this.likeFlag='Yes';
        }
        else{
            this.likeFlag='No';
        }
      },(error) => {
        console.log(error);
      });
    }
  },
};
</script>

<style lang="scss" scoped></style>
