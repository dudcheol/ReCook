<template>
  <div class="pb-2">
    <v-card rounded="xl" width="150" class="text-center mr-4" outlined>
      <v-img
        src="https://news.kbs.co.kr/data/news/2017/01/04/3405677_bH6.jpg"
        max-height="150"
        min-height="150"
      />
      <v-container class="mb-3">
        <v-row>
          <v-col>
            <span class="font-weight-bold">
              {{ name }}
            </span>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="text-caption pa-0">
            <v-icon small>mdi-timer</v-icon>
            <span>
              {{ time }}
            </span>
            <v-icon small>mdi-account-outline</v-icon>
            <span>
              {{ person }}
            </span>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      name: '레피시 이름',
      time: '20분',
      person: '2인분',
    };
  },
  computed: {},
  watch: {},
  methods: {},
};
</script>

<style scoped></style>
