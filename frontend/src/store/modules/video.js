import { getVideoById } from '../../api/video';

// initial state
const state = () => ({
  videoInfo: {},
});

// getters
const getters = {};

// actions
const actions = {
  async addVideoById({ commit }, video_id) {
    await getVideoById(
      video_id,
      (response) => {
        commit('setVideoInfo', response.data);
      },
      () => {}
    );
  },
};

// mutations
const mutations = {
  setVideoInfo(state, payload) {
    state.videoInfo = payload;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
