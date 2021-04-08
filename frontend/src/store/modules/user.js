import { getUserAllergyIngredients } from '@/api/ingredients';
import {
  getUserInfoByAuthToken,
  getUserInfoByName,
  login,
  setAuthTokenToHeader,
  removeAuthTokenToHeader,
  getCheckSurvey,
} from '@/api/user';
import router from '@/routes';

// initial state
const state = () => ({
  user: {},
  mypageTabState: 'review',
  selectedUserInfo: {},
  allergyCheck: false,
  selectedIngredients: [],
  selectedBigIdx: 0,
  selectedSurveyFoodIdx: [],
  selectedSurveyAllergyIdx: [],
  searchTitle: '',
  beginAlert: false,
  drawerState: false,
  recentRecipe: [],
});

// getters
const getters = {
  hasAllergy(state) {
    return state.selectedSurveyAllergyIdx.length !== 0;
  },
};

// actions
const actions = {
  async LOGIN({ commit, dispatch }, userInfo) {
    let result = false;
    await login(
      userInfo,
      async (response) => {
        commit('setUser', response.data);
        localStorage.setItem('authToken', response.data['authToken']);
        setAuthTokenToHeader(response.data['authToken']);
        await dispatch('GET_USER_CHECK_SURVEY', response.data['userId']);
        await dispatch('GET_USER_ALLERGY_INGREDIENT', response.data['userId']);
        result = true;
      },
      (error) => {
        console.log('%cuser.js line:21 error', 'color: #007acc;', error);
      }
    );
    commit('clearRecentRecipe');
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
  async GET_USERINFO_BY_AUTHTOKEN({ commit, dispatch }, authToken) {
    setAuthTokenToHeader(authToken);
    await getUserInfoByAuthToken(
      async (response) => {
        response.data.user.authToken = authToken;
        commit('setUser', response.data.user);
        await dispatch('GET_USER_CHECK_SURVEY', response.data['userId']);
        await dispatch('GET_USER_ALLERGY_INGREDIENT', response.data['userId']);
      },
      () => {}
    );
    commit('clearRecentRecipe');
  },
  LOGOUT({ commit }) {
    removeAuthTokenToHeader();
    localStorage.removeItem('authToken');
    commit('setUser', {});
    commit('clearRecentRecipe');
    router.replace({ path: '/' });
  },
  async GET_USER_CHECK_SURVEY({ commit }, userId) {
    await getCheckSurvey(
      userId,
      (response) => {
        commit('setUserCheckSurvey', response.data);
      },
      () => {}
    );
  },
  async GET_USER_ALLERGY_INGREDIENT({ commit }, userId) {
    await getUserAllergyIngredients(
      userId,
      (response) => {
        commit('setUserAllergyIngredients', response.data);
      },
      () => {}
    );
  },
};

// mutations
const mutations = {
  setUser(state, payload) {
    state.user = payload;
  },
  setUserCheckSurvey(state, payload) {
    state.user['survey'] = payload;
  },
  setSelectedUserInfo(state, payload) {
    state.selectedUserInfo = payload;
  },
  setMypageTabState(state, payload) {
    state.mypageTabState = payload;
  },
  setAllergyCheck(state, payload) {
    state.allergyCheck = payload;
  },
  setSelectedIngredients(state, payload) {
    state.selectedIngredients = payload;
  },
  setSelectedBigIdx(state, payload) {
    state.selectedBigIdx = payload;
  },
  addIngredientItem(state, payload) {
    state.selectedIngredients.push(payload);
    const set = new Set(state.selectedIngredients);
    state.selectedIngredients = Array.from(set);
  },
  toggleAllergyCheck(state) {
    state.allergyCheck = !state.allergyCheck;
  },
  setSelectedSurveyFoodIdx(state, payload) {
    state.selectedSurveyFoodIdx = payload;
  },
  setSelectedSurveyAllergyIdx(state, payload) {
    state.selectedSurveyAllergyIdx = payload;
  },
  setUserAllergyIngredients(state, payload) {
    state.user.allergyList = payload;
  },
  setSearchTitle(state, payload) {
    state.searchTitle = payload;
  },
  isBeginAlertReaded(state, payload) {
    state.beginAlert = payload;
  },
  changeDrawerState(state, payload) {
    state.drawerState = payload;
  },
  clearRecentRecipe(state) {
    state.recentRecipe = [];
  },
  addRecentRecipe(state, payload) {
    const tmp = [];
    tmp.push(payload);
    for (let i = 0; i < state.recentRecipe.length; i++) {
      if (tmp.length >= 10) break;
      const e = state.recentRecipe[i];
      if (e['recipe-id'] !== payload['recipe-id']) {
        tmp.push(e);
      }
    }
    state.recentRecipe = tmp;
  },
};

export default {
  //   namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
