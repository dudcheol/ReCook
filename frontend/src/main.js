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
import InfiniteLoading from 'vue-infinite-loading';
import firebase from 'firebase/app';
import { FIREBASE_CONFIG } from '../config';

Vue.use(InfiniteLoading);
Vue.use(VueLottiePlayer);
Vue.use(VueInfiniteGrid);
Vue.use(VueAwesomeSwiper);

Vue.config.productionTip = false;

firebase.initializeApp(FIREBASE_CONFIG);

new Vue({
  router,
  vuetify,
  store,
  render: (h) => h(App),
}).$mount('#app');
