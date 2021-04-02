import {
  getUserInfoByAuthToken,
  getUserInfoByName,
  login,
  setAuthTokenToHeader,
  removeAuthTokenToHeader,
} from '@/api/user';

// initial state
const state = () => ({
  user: {},
  mypageTabState: 'review',
  selectedUserInfo: {},
});

// getters
const getters = {};

// actions
const actions = {
  async LOGIN({ commit }, userInfo) {
    let result = false;
    await login(
      userInfo,
      (response) => {
        console.log('%cuser.js line:21 response.data', 'color: #007acc;', response.data);
        commit('setUser', response.data);
        localStorage.setItem('authToken', response.data['authToken']);
        setAuthTokenToHeader(response.data['authToken']);
        result = true;
      },
      (error) => {
        console.log('%cuser.js line:21 error', 'color: #007acc;', error);
      }
    );
    return result;
  },
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
  async GET_USERINFO_BY_AUTHTOKEN({ commit }, authToken) {
    setAuthTokenToHeader(authToken);
    await getUserInfoByAuthToken(
      (response) => {
        response.data.user.authToken = authToken;
        commit('setUser', response.data.user);
      },
      () => {}
    );
  },
  LOGOUT() {
    removeAuthTokenToHeader();
    localStorage.setItem('authToken', undefined);
  },
};

// mutations
const mutations = {
  setUser(state, payload) {
    state.user = payload;
  },
  setSelectedUserInfo(state, payload) {
    state.selectedUserInfo = payload;
  },
  setMypageTabState(state, payload) {
    state.mypageTabState = payload;
  },
};

export default {
  //   namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
