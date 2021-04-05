<template>
  <div id="app">
    <v-app>
      <router-view class="fill-height"></router-view>
    </v-app>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {},
};
</script>

<style>
* {
  font-family: 'Spoqa Han Sans Neo', 'sans-serif' !important;
}

.h1 {
  font-weight: 300;
  font-size: 6rem;
  letter-spacing: -0.015625em;
}
.h2 {
  font-weight: 300;
  font-size: 3.75rem;
  letter-spacing: -0.05em;
  line-height: 1;
}
.h3 {
  font-weight: 400;
  font-size: 3rem;
  letter-spacing: normal;
}
.h4 {
  font-weight: 400;
  font-size: 2.125rem;
  letter-spacing: -0.0073529412em;
}
.h5 {
  font-weight: 400;
  font-size: 1.5rem;
  letter-spacing: normal;
}
.h6 {
  font-weight: 500;
  font-size: 1.25rem;
  letter-spacing: -0.0125em;
}
.subtitle-1 {
  font-weight: 400;
  font-size: 1.1rem;
  letter-spacing: -0.009375em;
}
.subtitle-2 {
  font-weight: 500;
  font-size: 0.875rem;
  letter-spacing: -0.0071428571em;
}
.caption-1 {
  font-weight: 400;
  font-size: 0.75rem;
  letter-spacing: -0.0333333333em;
}
.pointer {
  cursor: pointer;
}

.bg-color {
  background-color: #f8f9fb;
}
</style>
