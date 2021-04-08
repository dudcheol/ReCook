<template>
  <v-container fluid fill-height>
    <v-btn icon color="black" fixed style="top:12px" x-large @click="$router.go(-1)"
      ><v-icon>mdi-chevron-left</v-icon></v-btn
    >
    <v-row class="fill-height">
      <v-col class="d-flex flex-column align-center justify-center text-center">
        <div class="h1 d-flex align-center font-weight-black black--text">
          4<v-icon size="90" color="black">mdi-emoticon-confused</v-icon>4
        </div>
        <div class="h6 font-weight-black">찾을 수 없는 페이지입니다</div>
        <div class="pa-3 grey lighten-3 rounded-xl mt-2">
          요청하신 페이지가 사라졌거나,<br />잘못된 경로를 이용하셨어요
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {};
</script>
<style lang=""></style>
