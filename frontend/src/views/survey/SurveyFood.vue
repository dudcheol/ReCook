<template>
  <v-container>
    <v-row class="mt-14 pt-3">
      <v-col>
        <span class="h3 font-weight-thin"
          >어떤 <span class="font-weight-bold">음식</span>을<br />
          좋아하시나요?</span
        ><br />
        <span class="caption-1">취향에 맞은 음식을 선택해주세요. (중복선택가능)</span>
      </v-col>
    </v-row>
    <v-row class="mb-14 pb-3">
      <v-item-group v-model="selected" multiple class="d-flex flex-wrap">
        <v-col
          v-for="item in $store.state.survey.foodList"
          :key="'food' + item.recipeId"
          cols="6"
          md="4"
        >
          <v-item v-slot="{ active, toggle }">
            <div>
              <v-card
                class="d-flex flex-column align-center"
                rounded="xl"
                @click="toggle"
                elevation="0"
                outlined
              >
                <v-img
                  :src="item.recipeMainImage"
                  min-height="150"
                  max-height="150"
                  :gradient="active ? 'to top, rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0)' : ''"
                >
                  <div v-if="active" class="d-flex justify-center align-center fill-height">
                    <v-icon color="white" x-large>mdi-check-circle</v-icon>
                  </div>
                </v-img>
              </v-card>
              <div class="d-flex justify-center align-end pt-2">
                {{ item.recipeTitle }}
              </div>
            </div>
          </v-item>
        </v-col>
      </v-item-group>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  components: {},
  props: {},
  data() {
    return {};
  },
  computed: {
    selected: {
      get() {
        return this.$store.state.user.selectedSurveyFoodIdx;
      },
      set(value) {
        this.$store.commit('setSelectedSurveyFoodIdx', value);
      },
    },
  },
  watch: {},
  methods: {
    ...mapActions(['GET_FOOD_LIST']),
  },
  created() {
    this.GET_FOOD_LIST();
  },
};
</script>

<style>
.theme--light.v-btn.v-btn--disabled.v-btn--has-bg {
  background-color: gainsboro !important;
}
</style>
