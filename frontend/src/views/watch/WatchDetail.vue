<template>
  <div>
    <h2>{{ videoInfo.videoTitle }}</h2>

    <v-img
      min-height="200"
      max-height="200"
      :src="videoInfo.videoThumbnail"
      class="rounded-lg"
    >
    </v-img>

    <youtube 
      :video-id=getVideoId(videoInfo.videoUrl) 
      player-width="375" 
      player-height="200"
    >
    </youtube>

  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import Vue from 'vue';
import VueYouTubeEmbed from 'vue-youtube-embed';
// import { getIdFromURL } from 'vue-youtube-embed';
 
Vue.use(VueYouTubeEmbed)

export default {
  components: {},
  props: {},
  data() {
    return {
      videoId: '',
    };
  },
  computed: {
    ...mapState({
      videoInfo: (state) => state.video.videoInfo,
    }),
  },
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        this.addVideoById(value.params['video_id']);
        console.log(
          '%cWatchDetail.vue line:19 value.params',
          'color: #007acc;',
          value.params['video_id']
        );
      },
    },
  },
  methods: {
    ...mapActions(['addVideoById']),
    getVideoId (url) {
      this.videoId = this.$youtube.getIdFromURL(url)
    },
    ready (event) {
      this.player = event.target
    },
    playing () {
    },
    stop () {
      this.player.stopVideo()
    },
    pause () {
      this.player.pauseVideo()
    }
  },
};
</script>

<style scoped></style>
