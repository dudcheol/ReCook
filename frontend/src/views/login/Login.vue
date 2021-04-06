<template>
  <v-container fill-height class=" pt-14">
    <v-row class="py-14">
      <v-col class="pa-0">
        <v-row>
          <v-col>
            <div class="pl-3">
              <span class="h2 font-weight-thin"
                ><span class="font-weight-black">로그인</span>이<br />
                필요한<br />
                서비스입니다</span
              >
            </div>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col>
            <VueLottiePlayer
              name="loading"
              loop
              width="100%"
              height="100%"
              path="https://assets3.lottiefiles.com/private_files/lf30_3dLrkA.json"
            />
          </v-col>
        </v-row>
        <v-row no-gutters style="position:fixed; bottom:0; width:100%" class="mb-6">
          <v-col class="d-flex align-center justify-center">
            <div @click="googleLogin">
              <v-btn outlined color="grey" class="white py-7 px-10" elevation="24" rounded>
                <v-img
                  src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/512px-Google_%22G%22_Logo.svg.png"
                  height="18"
                  width="18"
                  contain
                ></v-img>
                <span class="black--text subtitle-2 pl-3" style="font-size:14px"
                  >Google 계정으로 로그인</span
                >
              </v-btn>
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import firebase from 'firebase';
import VueLottiePlayer from 'vue-lottie-player';
import { mapActions } from 'vuex';

export default {
  components: {
    VueLottiePlayer,
  },
  props: {},
  data() {
    return {
      prevRoute: null,
      windowWidth: 0,
    };
  },
  computed: {},
  watch: {},
  methods: {
    ...mapActions(['LOGIN']),
    googleLogin() {
      const provider = new firebase.auth.GoogleAuthProvider();
      firebase
        .auth()
        .signInWithPopup(provider)
        .then(async (response) => {
          const result = await this.LOGIN({
            userGid: response.user.uid,
            userEmail: response.user.email,
            userName: response.user.email.split('@')[0],
            userImage: response.user.photoURL,
          });

          if (result) {
            this.$router.go(-1);
          } else {
            alert('로그인에 실패했습니다.');
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  mounted() {
    this.windowWidth = window.innerWidth;
    console.log('%cLogin.vue line:95 window.innerHeight', 'color: #007acc;', this.windowWidth);
  },
};
</script>

<style scoped></style>
