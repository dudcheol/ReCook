<template>
  <v-bottom-navigation ref="nav" v-model="selectedView" grow fixed flat>
    <v-btn class="bottom-btn" value="Main">
      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="Feed">
      <v-icon>mdi-view-dashboard</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="Fridge" x-large @click="sheet = !sheet">
      <v-icon large>mdi-fridge-outline</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="Watch">
      <v-icon>mdi-play-circle</v-icon>
    </v-btn>

    <v-btn class="bottom-btn" value="Mypage">
      <v-icon>mdi-account</v-icon>
    </v-btn>

    <v-bottom-sheet v-model="sheet">
      <v-card class="rounded-t-xl pa-1">
        <v-list>
          <v-subheader>
            <span class="text-h5 font-weight-black black--text">나의 냉장고</span>
          </v-subheader>
          <v-list-item v-for="tile in tiles" :key="tile.title" @click="sheet = false">
            <!-- <v-list-item-avatar>
              <v-avatar size="32px" tile>
                <img
                  :src="`https://cdn.vuetifyjs.com/images/bottom-sheets/${tile.img}`"
                  :alt="tile.title"
                />
              </v-avatar>
            </v-list-item-avatar> -->
            <v-list-item-title class="pl-3" @click="$router.push({ name: tile.type })">{{
              tile.title
            }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-card>
    </v-bottom-sheet>
  </v-bottom-navigation>
</template>

<script>
import { mapState } from 'vuex';
export default {
  components: {},
  props: {},
  data() {
    return {
      sheet: false,
      tiles: [
        {
          // img: 'messenger.png',
          title: '남은 재료 고르기',
          type: 'FridgeIngredient',
        },
        {
          // img: 'google.png',
          title: '남은 재료로 레시피 추천받기',
          type: 'FridgeRecomm',
        },
      ],
    };
  },
  computed: {
    ...mapState({
      pageName: (state) => state.header.pageName,
    }),
    selectedView: {
      get() {
        return this.pageName;
      },
      set(value) {
        if (value === 'Fridge') {
          return;
        }
        if (value === 'Mypage') {
          const userName = 'userName';
          this.$router.replace({ path: `${userName}` }).catch(() => {});
          return;
        }
        this.$router.replace({ name: value }).catch(() => {});
      },
    },
  },
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        this.$store.commit('setPageName', value.name);
      },
    },
  },
  methods: {
    fridgeActive() {
      console.log('%cFooter.vue line:97 active!!', 'color: #007acc;');
    },
  },
};
</script>

<style scope>
.v-btn.bottom-btn {
  min-width: 20px !important;
  font-size: 1px;
}
</style>
