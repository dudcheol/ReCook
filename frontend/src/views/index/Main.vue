<template>
  <v-container fluid>
    <v-row>
      <v-col class="pa-0">
        <div class="banner"></div>
      </v-col>
    </v-row>
    <!-- 추천 레시피 -->
    <v-row>
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center">
            <span class="text-h5 font-weight-black">추천 Recipe</span>
            <v-btn text small class="pa-1">전체보기</v-btn>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <div class="recipe-box"></div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <!-- 인기 레시피 -->
    <v-row>
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center">
            <span class="text-h5 font-weight-black">인기 Recipe</span>
            <v-btn text small class="pa-1">전체보기</v-btn>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <div class="recipe-box"></div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <!-- 최신 레시피 -->
    <v-row>
      <v-col>
        <v-row>
          <v-col class="d-flex justify-space-between align-center">
            <span class="text-h5 font-weight-black">최신 Recipe</span>
            <v-btn text small class="pa-1">전체보기</v-btn>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <div class="recipe-box"></div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {};
</script>

<style scoped>
.banner {
  height: 128px;
  background-color: red;
}
.recipe-box {
  height: 196px;
  background-color: yellow;
}
</style>
