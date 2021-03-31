import { getHashtagListByRecipeId } from '@/api/hashtag';

// initial state
const state = () => ({
  curRecipeHashtag: [],
});

// getters
const getters = {};

// actions
const actions = {
  GET_HASHTAGS_BY_RECIPE_ID({ commit }, recipeId) {
    getHashtagListByRecipeId(
      recipeId,
      (response) => {
        commit('setCurRecipeHashtag', response.data);
      },
      (error) => {
        console.log('%chashtag.js line:20 error', 'color: #007acc;', error);
      }
    );
  },
};

// mutations
const mutations = {
  setCurRecipeHashtag(state, payload) {
    state.curRecipeHashtag = payload;
  },
};

export default {
  //   namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
