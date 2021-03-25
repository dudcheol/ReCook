import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

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
        path: 'mypage',
        name: 'Mypage',
        component: () => import('@/views/index/Mypage'),
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
        // path: 'review/:review_id',
        path: 'review',
        name: 'ReviewDetail',
        component: () => import('@/views/user/ReviewDetail'),
      },
    ],
  },
];

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
