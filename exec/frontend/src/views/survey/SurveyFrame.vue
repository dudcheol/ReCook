<template>
  <v-container fluid fill-height class="pa-0" :class="bgClass">
    <router-view></router-view>
    <v-btn fixed fab :icon="!btnDark" top @click="btnBack" :dark="!btnDark" class="ml-3"
      ><v-icon>mdi-{{ btnStyle }}</v-icon></v-btn
    >
    <div
      style="position:fixed; bottom:0; width:100%"
      class="d-flex align-center justify-center mb-4 px-4"
    >
      <div class="swiper-pagination pb-10 mb-14" slot="pagination"></div>
      <v-btn
        @click="btnFunc"
        rounded
        depressed
        block
        x-large
        :color="btnColor"
        :disabled="$route.path == '/survey/food' && !$store.state.user.selectedSurveyFoodIdx.length"
        :class="btnDark ? 'white--text' : 'dahong--text'"
        >{{
          btnText === 'Allergy' ? (hasAllergy ? '선택 완료' : '알러지가 없습니다') : btnText
        }}</v-btn
      >
    </div>
    <v-overlay :value="overlay">
      <div class="rounded-circle white">
        <LoadingCheers :width="128" :height="128" />
      </div>
      <div class="pt-2 text-center caption font-weight-bold">정보 저장 중..</div>
    </v-overlay>
  </v-container>
</template>

<script>
import LoadingCheers from '@/components/common/LoadingCheers.vue';
import { mapActions, mapGetters } from 'vuex';
export default {
  components: { LoadingCheers },
  props: {},
  data() {
    return {
      btnStyle: 'close',
      btnText: '시작하기',
      btnBack: () => {},
      btnFunc: () => {},
      btnColor: 'white',
      bgClass: 'dahong white--text',
      btnDark: false,
      btnDisabled: false,
      overlay: false,
    };
  },
  computed: {
    ...mapGetters(['hasAllergy']),
  },
  watch: {
    '$route.path': {
      immediate: true,
      handler(value) {
        switch (value) {
          case '/survey':
            this.btnStyle = 'close';
            this.btnText = '시작하기';
            this.btnColor = 'white';
            this.bgClass = 'dahong white--text';
            this.btnDark = false;
            this.btnBack = () => {
              this.LOGOUT();
              this.$router.replace({ path: '/' });
            };
            this.btnFunc = () => {
              this.$router.replace({ path: '/survey/food' });
            };
            break;
          case '/survey/food':
            this.btnStyle = 'chevron-left';
            this.btnText = '다음';
            this.btnColor = 'dahong';
            this.bgClass = 'white';
            this.btnDark = true;
            this.btnBack = () => {
              this.$router.replace({ path: '/survey' });
            };
            this.btnFunc = () => {
              this.$router.replace({ path: '/survey/allergy' });
            };
            break;
          case '/survey/allergy':
            this.btnStyle = 'chevron-left';
            this.btnText = 'Allergy';
            this.btnColor = 'dahong';
            this.bgClass = 'white';
            this.btnDark = true;
            this.btnBack = () => {
              this.$router.replace({ path: '/survey/food' });
            };
            this.btnFunc = () => {
              this.saveSurvey();
            };
            break;
          default:
            break;
        }
      },
    },
  },
  methods: {
    ...mapActions(['SAVE_SURVEY_RESULT', 'LOGOUT']),
    async saveSurvey() {
      this.overlay = true;
      if (await this.SAVE_SURVEY_RESULT()) {
        this.$router.replace({ path: '/' });
      } else {
        alert('오류가 발생했습니다');
      }
      this.overlay = false;
    },
  },
};
</script>

<style>
:root {
  --swiper-theme-color: white;
}
.swiper-pagination-bullet {
  margin: 0 10px 0 10px;
}
</style>
