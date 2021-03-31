import { getReviewById, getReviewByUserName } from '@/api/review';

// initial state
const state = () => ({
  reviewInfo: {},
  selectedUserReviewList: [],
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
  GET_REVIEW_LIST_BY_USERNAME({ commit }, userName) {
    getReviewByUserName(
      userName,
      (response) => {
        commit('setSelectedUserReviewList', response.data);
      },
      (error) => {
        console.log(
          '%cerror review.js line:32 ',
          'color: red; display: block; width: 100%;',
          error
        );
      }
    );
  },
};

// mutations
const mutations = {
  setReviewInfo(state, payload) {
    state.reviewInfo = payload;
  },
  clearReviewInfo(state) {
    state.reviewInfo = {};
  },
  setSelectedUserReviewList(state, payload) {
    state.selectedUserReviewList = payload;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
