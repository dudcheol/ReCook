import Vue from 'vue';
import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css';

Vue.use(Vuetify);

const opts = {
  theme: {
    themes: {
      light: {
        dahong: '#FF4B3A',
        yellow: '#F9C815',
        orange: '#FF5F2E',
        whitegrey: '#F8F9FB',
        blackgrey: '#191919',
      },
      dark: {},
    },
  },
};

export default new Vuetify(opts);
