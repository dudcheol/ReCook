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

const routes = [
  {
    path: '/',
    component: () => import('@/views/Index'),
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
    ],
  },
  {
    path: '/',
    component: () => import('@/views/Newpage'),
    children: [
      {
        path: 'fridge/ingredient',
        name: 'FridgeIngredient',
        component: () => import('@/views/fridge/Ingredient'),
      },
      {
        path: 'fridge/recomm',
        name: 'FridgeRecomm',
        component: () => import('@/views/fridge/Fridge'),
      },
      {
        path: 'review/:review_id',
        name: 'ReviewDetail',
        component: () => import('@/views/review/ReviewDetail'),
      },
      {
        path: 'recipe/:recipe_id',
        name: 'RecipeDetail',
        component: () => import('@/views/recipe/RecipeDetail'),
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
];

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { x: 0, y: 0 };
    }
  },
});

export default router;
