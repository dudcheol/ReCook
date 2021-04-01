import { getBigList, getMiddleList, getSmallList } from '@/api/ingredients';

// initial state
const state = () => ({
  bigList: [],
  middleList: [],
  smallList: [],
  selectedIngredients: [],
  selectedBigIdx: 0,
});

// getters
const getters = {};

// actions
const actions = {
  GET_BIG_LIST({ commit }) {
    getBigList(
      (response) => {
        commit('setBigList', response.data);
      },
      (error) => {
        console.log('%cingredients.js line:21 error', 'color: #007acc;', error);
      }
    );
  },
  GET_MIDDLE_LIST({ commit }, bigId) {
    getMiddleList(
      bigId,
      (response) => {
        commit('setMiddleList', response.data);
      },
      (error) => {
        console.log('%cingredients.js line:32 error', 'color: #007acc;', error);
      }
    );
  },
  GET_SMALL_LIST({ commit }, midId) {
    commit('setSmallList', []);
    getSmallList(
      midId,
      (response) => {
        commit('setSmallList', response.data);
      },
      (error) => {
        console.log('%cingredients.js line:43 error', 'color: #007acc;', error);
      }
    );
  },
  ADD_INGREDIENT_ITEM({ commit }, ingredient) {
    commit('addIngredientItem', ingredient);
  },
  REMOVE_INGREDIENT_ITEM({ commit }, ingredient) {
    commit('removeIngredientItem', ingredient);
  },
};

// mutations
const mutations = {
  setBigList(state, payload) {
    state.bigList = payload;
  },
  setMiddleList(state, payload) {
    state.middleList = payload;
  },
  setSmallList(state, payload) {
    state.smallList = payload;
  },
  addIngredientItem(state, payload) {
    state.selectedIngredients.push(payload);
    const set = new Set(state.selectedIngredients);
    state.selectedIngredients = Array.from(set);
  },
  removeIngredientItem(state, payload) {
    const idx = state.selectedIngredients.findIndex(function(item) {
      return item.smallId === payload;
    });
    if (idx > -1) state.selectedIngredients.splice(idx, 1);
  },
  setSelectedBigIdx(state, payload) {
    state.selectedBigIdx = payload;
  },
};

export default {
  //   namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
