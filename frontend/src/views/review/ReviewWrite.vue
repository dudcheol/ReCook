<template>
  <v-container fluid fill-height>
    <v-row>
      <v-col>사진영역</v-col>
    </v-row>

    <v-row>
      <v-col>내용입력</v-col>
    </v-row>

    <v-row>
      <v-col>작성 완료 버튼 영역</v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      reviewId: 0,
    };
  },
  computed: {},
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        this.reviewId = value.params['review_id'];
      },
    },
  },
  methods: {},
};
</script>

<style scoped></style>
