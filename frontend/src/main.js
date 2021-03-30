import Vue from 'vue';
import App from './App.vue';
import router from './routes';
import vuetify from './plugins/vuetify';
import store from './store/index';
import VueInfiniteGrid from '@egjs/vue-infinitegrid';
import VueAwesomeSwiper from 'vue-awesome-swiper';
import 'swiper/css/swiper.css';
import './utils/filters';
import VueLottiePlayer from 'vue-lottie-player';

Vue.use(VueLottiePlayer);
Vue.use(VueInfiniteGrid);
Vue.use(VueAwesomeSwiper);

Vue.config.productionTip = false;

new Vue({
  router,
  vuetify,
  store,
  render: (h) => h(App),
}).$mount('#app');
