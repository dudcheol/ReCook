<template>
  <v-container>
    <v-row class="mt-14 pt-3">
      <v-col>
        <span class="h3 font-weight-thin"
          ><span class="font-weight-bold">알레르기</span>가<br />
          있으신가요?</span
        ><br />
        <span class="caption-1">먹을 수 없는 음식을 선택해주세요. (중복선택가능)</span>
      </v-col>
    </v-row>
    <v-row class="mb-14 pb-3">
      <v-item-group v-model="selected" multiple class="d-flex flex-wrap">
        <v-col v-for="item in $store.state.survey.allergyList" :key="item.recipeId" cols="6" md="4">
          <v-item v-slot="{ active, toggle }">
            <!-- <v-card
              :color="active ? 'deep-orange lighten-5' : ''"
              class="d-flex align-center py-4 align-center justify-center"
              rounded="lg"
              @click="toggle"
            >
              <span :class="active ? 'font-weight-bold' : 'font-weight-regular'">
                {{ item.split(' ')[0] }}
              </span>
            </v-card> -->
            <div>
              <v-card class="d-flex align-center" rounded="xl" outlined @click="toggle">
                <v-img
                  :src="item.allergyImage"
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
                {{ item.allergyName.split(' ')[0] }}
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
        return this.$store.state.user.selectedSurveyAllergyIdx;
      },
      set(value) {
        this.$store.commit('setSelectedSurveyAllergyIdx', value);
      },
    },
  },
  watch: {},
  methods: {
    ...mapActions(['GET_ALLERGY_LIST']),
  },
  created() {
    this.GET_ALLERGY_LIST();
  },
};
</script>

<style scoped></style>
