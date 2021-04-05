import { getVideoById } from '../../api/video';

// initial state
const state = () => ({

  videoInfo : {},
  
});

// getters
const getters = {};

// actions
const actions = {

  async addVideoById({ commit }, video_id) {
    await getVideoById(
      video_id,
      (response) => {
        // console.log(response.data);
        commit('setVideoInfo', response.data);
      },
      (error) => {
        console.log('%crecipe.js line:21 error', 'color: #007acc;', error);
      }
    );
  },

};

// mutations
const mutations = {

  setVideoInfo(state, payload) {
    console.log(state.videoInfo);
    state.videoInfo = payload;
  },

};

export default {
  state,
  getters,
  actions,
  mutations,
};
