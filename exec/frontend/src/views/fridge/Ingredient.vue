<template>
  <v-container class="pa-0">
    <div class="footer px-5 pb-3">
      <v-card rounded="xl" elevation="15">
        <v-card-title>
          <div class="d-flex align-center justify-space-between" style="width:100%">
            <span class="pl-1">
              선택된 재료 총
              <span class="dahong--text font-weight-black">{{ selectedIngredients.length }}</span
              >/3 개</span
            >
            <v-btn icon @click="isExpanded = !isExpanded">
              <v-icon>mdi-unfold-{{ isExpanded ? 'less' : 'more' }}-horizontal</v-icon>
            </v-btn>
          </div></v-card-title
        >
        <v-chip-group
          active-class="none"
          v-if="selectedIngredients.length"
          class="px-3"
          :column="isExpanded"
        >
          <v-chip
            color="grey lighten-3"
            text-color="grey darken-2"
            v-for="(ingredient, index) in selectedIngredients"
            :key="ingredient.smallName + index"
            @click:close="REMOVE_INGREDIENT_ITEM(ingredient.smallId)"
            @click="REMOVE_INGREDIENT_ITEM(ingredient.smallId)"
            close
          >
            {{ ingredient.smallName }}
          </v-chip>
        </v-chip-group>
        <v-sheet
          v-else
          class="d-flex justify-center grey--text py-3 mx-4"
          rounded="lg"
          color="grey lighten-3"
        >
          남은 재료를 선택해주세요
        </v-sheet>
        <v-card-actions>
          <v-btn
            color="dahong"
            text
            rounded
            block
            :disabled="
              selectedIngredients.length == 0 || selectedIngredients.length > 3 ? true : false
            "
            @click="$router.push({ path: `/fridge/recomm` })"
          >
            {{
              selectedIngredients.length > 3 ? '재료는 3개 이상 선택할 수 없어요' : '요리 시작하기'
            }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
    <v-row>
      <v-col
        cols="3"
        :style="{ maxHeight: windowHeight + 'px', overflow: 'auto', paddingBottom: 200 + 'px' }"
        class="px-0"
      >
        <v-list class="pa-0">
          <v-list-item-group :value="selectedBigIdx" mandatory color="dahong">
            <v-list-item
              v-for="(item, i) in bigList"
              :key="'big' + i"
              class="py-0"
              @click="
                GET_MIDDLE_LIST(item.bigId);
                $store.commit('setSelectedBigIdx', i);
              "
              :disabled="i === bigList.length - 1"
            >
              <span class="sub-title-2 pl-1 font-weight-bold">{{ item.bigName }}</span>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-col>
      <v-col
        :style="{ maxHeight: windowHeight + 'px', overflow: 'auto', paddingBottom: 200 + 'px' }"
        class="px-0"
      >
        <v-list class="pa-0" flat>
          <v-list-group
            v-for="item in middleList"
            :key="'mid' + item.midId"
            color="yellow"
            :ripple="false"
            @click="GET_SMALL_LIST(item.midId)"
            class="font-weight-black grey lighten-4"
          >
            <template v-slot:activator>
              <v-list-item-title>{{ item.middleName }}</v-list-item-title>
            </template>
            <v-list-item
              v-for="item in smallList"
              :key="'small' + item.smallId"
              link
              @click="ADD_INGREDIENT_ITEM(item)"
              class="white"
            >
              <v-list-item-content>
                <v-list-item-title
                  v-text="item.smallName"
                  class="px-2 sub-title-2"
                ></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
        </v-list>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {
  components: {},
  props: {},
  data() {
    return {
      isExpanded: true,
      getIngredients: Set,
    };
  },
  computed: {
    ...mapState({
      bigList: (state) => state.ingredients.bigList,
      middleList: (state) => state.ingredients.middleList,
      smallList: (state) => state.ingredients.smallList,
      selectedIngredients: (state) => state.user.selectedIngredients,
      selectedBigIdx: (state) => state.user.selectedBigIdx,
    }),
  },
  watch: {
    $route: {
      immediate: true,
      handler() {
        this.GET_BIG_LIST();
        this.GET_MIDDLE_LIST(this.selectedBigIdx + 1);
      },
    },
  },
  methods: {
    ...mapActions([
      'GET_BIG_LIST',
      'GET_MIDDLE_LIST',
      'GET_SMALL_LIST',
      'ADD_INGREDIENT_ITEM',
      'REMOVE_INGREDIENT_ITEM',
    ]),
  },
  created() {
    this.windowHeight = window.innerHeight - 56;
  },
};
</script>

<style scoped>
.footer {
  width: 100%;
  bottom: 0;
  position: fixed;
  z-index: 3;
}
</style>
