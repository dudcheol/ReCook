import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

import user from './modules/user';
import recipe from './modules/recipe';
import ingredients from './modules/ingredients';
import header from './modules/header';

const store = new Vuex.Store({
  modules: {
    user,
    recipe,
    ingredients,
    header,
  },
});

export default store;
