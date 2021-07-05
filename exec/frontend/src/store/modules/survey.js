import { getAllergySurveyList, getFoodSurveyList, saveSurveyResult } from '@/api/survey';

// initial state
const state = () => ({
  foodList: [],
  allergyList: [],
});

// getters
const getters = {
  selectedSurveyFoods(state, getters, rootState) {
    const idxs = rootState.user.selectedSurveyFoodIdx;
    const ret = [];
    let idx = 0;
    state.foodList.forEach((e, i) => {
      if (i == idxs[idx]) {
        ret.push(e.recipeTitle);
        idx++;
      }
    });
    return ret;
  },
  selectedSurveyAllergys(state, getters, rootState) {
    const idxs = rootState.user.selectedSurveyAllergyIdx;
    const ret = [];
    let idx = 0;
    state.allergyList.forEach((e, i) => {
      if (i == idxs[idx]) {
        ret.push(e.allergyName);
        idx++;
      }
    });
    return ret;
  },
};

// actions
const actions = {
  GET_FOOD_LIST({ commit }) {
    getFoodSurveyList(
      (response) => {
        commit('setFoodList', response.data);
      },
      () => {}
    );
  },
  GET_ALLERGY_LIST({ commit }) {
    getAllergySurveyList(
      (response) => {
        commit('setAllergyList', response.data);
      },
      () => {}
    );
  },
  async SAVE_SURVEY_RESULT({ getters }) {
    let res = false;
    await saveSurveyResult(
      this.state.user.user.userId,
      getters.selectedSurveyFoods,
      getters.selectedSurveyAllergys,
      (response) => {
        this.state.user.user.survey = response.data;
        res = true;
      },
      () => {}
    );
    return res;
  },
};

// mutations
const mutations = {
  setFoodList(state, payload) {
    state.foodList = payload;
  },
  setAllergyList(state, payload) {
    state.allergyList = payload;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
