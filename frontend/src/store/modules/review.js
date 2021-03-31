import { getReviewById } from '@/api/review';

// initial state
const state = () => ({
  reviewInfo: {},
});

// getters
const getters = {};

// actions
const actions = {
  GET_REVIEW_INFO({ commit }, reviewId) {
    getReviewById(
      reviewId,
      (response) => {
        commit('setReviewInfo', response.data);
      },
      (error) => {
        console.log('%creview.js line:20 error', 'color: #007acc;', error);
      }
    );
  },
};

// mutations
const mutations = {
  setReviewInfo(state, payload) {
    state.reviewInfo = payload;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
