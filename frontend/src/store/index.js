import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

import recipe from './modules/recipe';
import ingredients from './modules/ingredients';
import header from './modules/header';
import review from './modules/review';
import hashtag from './modules/hashtag';
import user from './modules/user';
import video from './modules/video';

const store = new Vuex.Store({
  modules: {
    recipe,
    ingredients,
    header,
    review,
    hashtag,
    user,
    video,
  },
});

export default store;
