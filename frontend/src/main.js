import Vue from 'vue';
import App from './App.vue';
import router from './routes';
import vuetify from './plugins/vuetify';
import store from './store/index';
import VueInfiniteGrid from '@egjs/vue-infinitegrid';

Vue.use(VueInfiniteGrid);

Vue.config.productionTip = false;

new Vue({
  router,
  vuetify,
  store,
  render: (h) => h(App),
}).$mount('#app');
