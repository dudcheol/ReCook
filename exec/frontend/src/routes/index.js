import Vue from 'vue';
import Router from 'vue-router';
import store from '../store';

Vue.use(Router);

const requireAuth = () => async (to, from, next) => {
  let token = localStorage.getItem('authToken');
  if (store.state.user.user.authToken === undefined && token)
    await store.dispatch('GET_USERINFO_BY_AUTHTOKEN', token);
  if (store.state.user.user.authToken) next();
  else next('/login');
};

const requireSurvey = () => (to, from, next) => {
  if (store.state.user.user.authToken && store.state.user.user.survey === 'No') next('/survey');
  // if (store.state.user.user.authToken && store.state.user.user.survey === 'No') next();
  else next();
};

const routes = [
  {
    path: '/',
    component: () => import('@/views/Index'),
    beforeEnter: requireSurvey(),
    children: [
      {
        path: '',
        name: 'Main',
        component: () => import('@/views/index/Main'),
      },
      {
        path: 'feed',
        name: 'Feed',
        component: () => import('@/views/index/Feed'),
      },
      {
        path: 'watch',
        name: 'Watch',
        component: () => import('@/views/index/Watch'),
      },
      {
        path: '',
        name: 'Mypage',
        beforeEnter: requireAuth(),
        component: () => import('@/views/index/Mypage'),
        children: [
          {
            path: ':user_name/review',
            name: 'Mypage#Review',
            component: () => import('@/views/user/UserReview'),
          },
          {
            path: ':user_name/like',
            name: 'Mypage#Like',
            component: () => import('@/views/user/UserLike'),
          },
        ],
      },
      {
        path: 'watch/:video_id',
        name: 'Watch#Detail',
        component: () => import('@/views/watch/WatchDetail'),
      },
      {
        path: 'user/:user_name',
        name: 'Feed#User',
        component: () => import('@/views/user/UserReview'),
      },
      {
        path: 'review/:review_id',
        name: 'Feed#Detail',
        component: () => import('@/views/review/ReviewDetail'),
      },
      {
        path: 'search',
        name: 'Main#Search',
        component: () => import('@/views/index/Search'),
      },
      {
        path: 'fridge/recomm',
        name: 'Fridge',
        component: () => import('@/views/fridge/Recommend'),
      },
    ],
  },
  {
    path: '/',
    component: () => import('@/views/Newpage'),
    beforeEnter: requireSurvey(),
    children: [
      {
        path: 'fridge/ingredient',
        name: 'FridgeIngredient',
        component: () => import('@/views/fridge/Ingredient'),
      },
      {
        path: 'recipe/:recipe_id',
        name: 'RecipeDetail',
        component: () => import('@/views/recipe/RecipeDetail'),
      },
      {
        path: 'recipe/list/:type',
        name: 'RecipeList',
        // beforeEnter: requireAuth(),
        component: () => import('@/views/recipe/RecipeList'),
      },
      {
        path: 'recipe/:recipe_id/write',
        name: 'ReviewWrite',
        beforeEnter: requireAuth(),
        component: () => import('@/views/review/ReviewWrite'),
      },
      {
        path: 'login',
        name: 'Login',
        component: () => import('@/views/login/Login'),
      },
    ],
  },
  {
    path: '/survey',
    name: 'Survey',
    component: () => import('@/views/survey/SurveyFrame'),
    children: [
      {
        path: '',
        name: 'SurveyMain',
        component: () => import('@/views/survey/SurveyMain'),
      },
      {
        path: 'allergy',
        name: 'SurveyAllergy',
        component: () => import('@/views/survey/SurveyAllergy'),
      },
      {
        path: 'food',
        name: 'SurveyFood',
        component: () => import('@/views/survey/SurveyFood'),
      },
    ],
  },
  {
    path: '/404',
    name: 'notFound',
    component: () => import('@/views/NotFound'),
  },
  {
    path: '*',
    redirect: '/404',
  },
];

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
