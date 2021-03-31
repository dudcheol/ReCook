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
        path: '',
        name: 'Mypage',
        component: () => import('@/views/index/Mypage'),
        children: [
          {
            path: ':user_name',
            name: 'Mypage',
            component: () => import('@/views/user/UserReview'),
          },
          {
            path: ':user_name/like',
            name: 'Mypage',
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
        component: () => import('@/views/review/ReviewWrite'),
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
