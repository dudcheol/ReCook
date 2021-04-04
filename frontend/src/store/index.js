import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

import recipe from './modules/recipe';
import ingredients from './modules/ingredients';
import header from './modules/header';
import review from './modules/review';
import hashtag from './modules/hashtag';
import user from './modules/user';
import video from './modules/video';
import survey from './modules/survey';

const store = new Vuex.Store({
  modules: {
    recipe,
    ingredients,
    header,
    review,
    hashtag,
    user,
    video,
    survey,
  },
  plugins: [
    createPersistedState({
      paths: ['user'],
    }),
  ],
});

export default store;
