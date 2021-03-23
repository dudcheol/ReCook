<template>
  <div id="app">
    <v-app>
      <router-view></router-view>
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
  font-family: 'Spoqa Han Sans Neo', 'sans-serif';
}

.pointer {
  cursor: pointer;
}

.bg-color {
  background-color: #f8f9fb;
}
</style>
