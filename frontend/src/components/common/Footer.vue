<template>
  <v-bottom-navigation v-model="selectedView" grow fixed flat>
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
            <v-list-item-title class="pl-3" @click="openPage(tile.name, tile.type)">{{
              tile.title
            }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-card>
    </v-bottom-sheet>
  </v-bottom-navigation>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      sheet: false,
      selectedView: 'Main',
      tiles: [
        {
          img: 'messenger.png',
          title: '남은 재료 고르기',
          name: 'Fridge',
          type: 'fridge-ingredient',
        },
        {
          img: 'google.png',
          title: '남은 재료로 추천받기',
          name: 'Fridge',
          type: 'fridge-recomm',
        },
      ],
    };
  },
  computed: {},
  watch: {
    selectedView: function(val) {
      if (val == 'Fridge') return;
      this.$router.replace({ name: val });
    },
  },
  methods: {
    openPage(name, type) {
      this.$router.push({ name, params: { type } });
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
