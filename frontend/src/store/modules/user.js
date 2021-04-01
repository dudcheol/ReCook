import { getUserInfoByName } from '@/api/user';

// initial state
const state = () => ({
  selectedUserInfo: {},
});

// getters
const getters = {};

// actions
const actions = {
  GET_USERINFO_BY_NAME({ commit }, userName) {
    getUserInfoByName(
      userName,
      (response) => {
        commit('setSelectedUserInfo', response.data);
      },
      (error) => {
        console.log('%cuser.js line:17 error', 'color: #007acc;', error);
      }
    );
  },
};

// mutations
const mutations = {
  setSelectedUserInfo(state, payload) {
    state.selectedUserInfo = payload;
  },
};

export default {
  //   namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
