<template>
  <div class="pb-2">
    <v-card rounded="xl" width="150" class="text-center mr-4" outlined>
      <v-img
        src="https://lh3.googleusercontent.com/proxy/I12hFRFealT8Ol4CupTEmqXE0yFMb_ouy11p8Gp_LshlwbsitHRP4AuKKYVsP6MeVEs3PJFToQJ7DLLMsGzsVUol-Pxnj5P5Rn3RsceylJAd6UNrXLsmq10yeB_YwowhP2t_ziAW5wtA22vGwEvGpAtNr5mWh_1b6elG63DrfsdRZreVAR7x00G3zR4_QEEdFfeg4E_qCB883BTtDeEoLU9EQnesl_lbw14SJQ185En7msYA7QaAXHuzFKfzGyt0FbM8DJREBluUEzuBA_ZZSt4KhOBknfkBskYAy6Lf-O1DgEJODb47uSHI3m-ru1LNWXM-fPA8u83a9Y6OzxwSecGfzrPcdVrlTlk_mbjEWu6pVoKAMuy76bDmVkIafY_FR-9QlBzsHVcU"
        max-height="150"
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
