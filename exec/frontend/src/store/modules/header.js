// initial state
const state = () => ({
  pageName: 'Main',
});

// getters
const getters = {};

// actions
const actions = {};

// mutations
const mutations = {
  setPageName(state, payload) {
    state.pageName = payload;
  },
};

export default {
  //   namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
