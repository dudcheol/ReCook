import {
  getRecipeById,
  getRecipeNewList,
  getRecipeHotList,
  getRecommRecipeByIngredients,
  getRecommRecipeListByUser,
  getRecommRecipeByRecipeTitle,
  getRecipeHotListAll,
  getRecipeNewListAll,
} from '../../api/recipe';

// initial state
const state = () => ({
  recipeInfo: {},
  recipeNewList: [],
  recipeNewListAll: [],
  recipeHotList: [],
  recipeHotListAll: [],
  recipeRecomm: [],
  recipeRecommMainList: [],
  recipeRecommRelateList: [],
});

// getters
const getters = {};

// actions
const actions = {
  async addRecipeById({ commit }, recipe_id) {
    await getRecipeById(
      recipe_id,
      (response) => {
        commit('setRecipeInfo', response.data);
      },
      (error) => {
        console.log('%crecipe.js line:21 error', 'color: #007acc;', error);
      }
    );
  },
  addRecipeNewList({ commit }) {
    getRecipeNewList(
      (response) => {
        commit('setRecipeNewList', response.data);
      },
      (error) => {
        console.log('%crecipe.js line:19 error', 'color: #007acc;', error);
      }
    );
  },
  addRecipeNewListAll({ commit }, page, size) {
    getRecipeNewListAll(
      page,
      size,
      (response) => {
        commit('setRecipeNewListAll', response.data);
      },
      (error) => {
        console.log('%crecipe.js line:19 error', 'color: #007acc;', error);
      }
    );
  },
  addRecipeHotList({ commit }) {
    getRecipeHotList(
      (response) => {
        commit('setRecipeHotList', response.data);
      },
      (error) => {
        console.log('%crecipe.js line:19 error', 'color: #007acc;', error);
      }
    );
  },
  addRecipeHotListAll({ commit }, page, size) {
    getRecipeHotListAll(
      page,
      size,
      (response) => {
        commit('setRecipeHotListAll', response.data);
      },
      (error) => {
        console.log('%crecipe.js line:19 error', 'color: #007acc;', error);
      }
    );
  },
  GET_RECOMM_RECIPE_LIST_BY_USER({ commit }, userId) {
    console.log('추천 레시피 리스트를 받아오고 있습니다.');
    getRecommRecipeListByUser(
      userId,
      (response) => {
        console.log('%crecipe.js line:88 response.data', 'color: #007acc;', response.data);
        commit('setRecipeRecommMainList', response.data);
      },
      () => {}
    );
  },
  GET_RECOMM_RECIPE_BY_RECIPETITLE({ commit }, recipeTitle, userId) {
    console.log('현재 레시피와 관련된 추천 레시피 리스트를 받아오고 있습니다.');
    getRecommRecipeByRecipeTitle(
      recipeTitle,
      userId,
      (response) => {
        commit('setRecipeRecommRelateList', response.data);
      },
      () => {}
    );
  },
  GET_RECOMM_RECIPE_LIST_BY_INGREDIENTS({ commit }, info) {
    commit('clearRecipeRecommList');
    getRecommRecipeByIngredients(
      info.ingredientList.map((v) => {
        return v.smallName;
      }),
      info.userId,
      info.allergy,
      (response) => {
        commit('setRecipeRecomm', response.data);
      },
      () => {}
    );
  },
};

// mutations
const mutations = {
  setRecipeInfo(state, payload) {
    state.recipeInfo = payload;
  },
  setRecipeNewList(state, payload) {
    state.recipeNewList = payload;
  },
  setRecipeNewListAll(state, payload) {
    state.recipeNewListAll.push(...payload);
  },
  setRecipeHotList(state, payload) {
    state.recipeHotList = payload;
  },
  setRecipeHotListAll(state, payload) {
    state.recipeHotListAll.push(...payload);
  },
  clearRecipeInfo(state) {
    state.recipeInfo = {};
  },
  setRecipeRecomm(state, payload) {
    state.recipeRecomm = payload;
  },
  clearRecipeRecommList(state) {
    state.recipeRecomm = [];
  },
  setRecipeRecommMainList(state, payload) {
    state.recipeRecommMainList = payload;
  },
  setRecipeRecommRelateList(state, payload) {
    state.recipeRecommRelateList = payload;
  },
  clearRecipeRecommRelateList(state) {
    state.recipeRecommRelateList = [];
  },
};

export default {
  //   namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
