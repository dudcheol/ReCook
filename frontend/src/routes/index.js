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
    path: '/page',
    component: () => import('@/views/Newpage'),
    children: [
      {
        path: 'fridge',
        name: 'Fridge',
        component: () => import('@/views/fridge/Fridge'),
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
