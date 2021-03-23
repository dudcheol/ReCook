<template>
  <div>
    <Header :leftBtn="left" :title="center" :rightBtn="right" />
    <router-view></router-view>
  </div>
</template>

<script>
import Header from '@/components/common/HeaderNewpage';
export default {
  components: {
    Header,
  },
  props: {},
  data() {
    return {
      left: '',
      right: '',
      center: '',
    };
  },
  computed: {},
  watch: {
    '$route.params.type': {
      immediate: true,
      handler(type) {
        switch (type) {
          case 'fridge-ingredient':
            this.left = 'close';
            this.center = '남은 재료 선택';
            this.right = '';
            break;
          case 'fridge-recomm':
            this.left = 'close';
            this.center = '남은 재료로 추천받기';
            this.right = '';
            break;
          case 'recipe-detail':
            this.left = 'chevron-left';
            this.center = '';
            this.right = 'heart';
            break;
          case 'review-detail':
            this.left = 'close';
            this.center = '';
            this.right = '';
            break;
          case 'profile-edit':
            this.left = 'close';
            this.center = '프로필 수정';
            this.right = '';
        }
      },
    },
  },
  methods: {},
};
</script>

<style scoped></style>
