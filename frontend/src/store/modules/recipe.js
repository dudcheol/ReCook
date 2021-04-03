import {
  getRecipeById,
  getRecipeNewList,
  getRecipeHotList,
  getRecommIngredients,
} from '../../api/recipe';

// initial state
const state = () => ({
  recipeInfo: {},
  recipeNewList: [],
  recipeHotList: [],
  recipeRecomm: [],
});

// getters
const getters = {};

// actions
const actions = {
  addRecipeById({ commit }, recipe_id) {
    getRecipeById(
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
  GET_RECOMM_RECIPE_LIST({ commit }, info) {
    commit('clearRecipeRecommList');
    getRecommIngredients(
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
  setRecipeHotList(state, payload) {
    state.recipeHotList = payload;
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
};

export default {
  //   namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
