import { getRecipeById, getRecipeNewList, getRecipeHotList } from '../../api/recipe';

// initial state
const state = () => ({
  recipeInfo: {},
  recipeNewList: [],
  recipeHotList: [],
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
};

export default {
  //   namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
