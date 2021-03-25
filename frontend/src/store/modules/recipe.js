import { getRecipeNewList } from '../../api/recipe';

// initial state
const state = () => ({
  recipeNewList: [],
});

// getters
const getters = {};

// actions
const actions = {
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
};

// mutations
const mutations = {
  setRecipeNewList(state, payload) {
    state.recipeNewList = payload;
  },
};

export default {
  //   namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
