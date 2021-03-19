// 임시
const userStore = {
  namespaced: true,
  state: {
    userName: '도로시',
  },
  getters: {
    GE_USER_NAME: (state) => state.userName,
  },
  mutations: {
    MU_USER_NAME: (state, payload) => {
      state.userName = payload.userName;
    },
  },
  actions: {
    AC_USER_NAME: ({ commit }, payload) => {
      commit('MU_USER_NAME', payload);
    },
  },
};

export default userStore;
