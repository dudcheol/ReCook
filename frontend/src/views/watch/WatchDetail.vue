<template>
  <div>
    <br>
    <youtube 
      :video-id="videoSubId"
      player-width="375" 
      player-height="200"
      @ready="ready"
      @playing="playing"
    />
    <br>
    <h4 style="margin: 10px">{{ videoInfo.videoTitle }}</h4>

    <v-row>
      <v-col cols="3" style="margin: 10px">
        <img :src="videoInfo.videoChannelImg" alt="왜 안돼">
      </v-col>

      <v-col style="margin: 10px">
        <div>{{ videoInfo.videoChannel }}</div>
      </v-col>
    </v-row>

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
    videoSubId(){ return this.videoId }
  },
  watch: {
    $route: {
      immediate: true,
      async handler(value) {
        await this.addVideoById(value.params['video_id']);
        console.log(
          '%cWatchDetail.vue line:19 value.params',
          'color: #007acc;',
          value.params['video_id']
        );
        this.getVideoId(this.videoInfo.videoUrl);
      },
    },
  },
  methods: {
    ...mapActions(['addVideoById']),
    
    ready (event) {
      this.player = event.target
    },
    getVideoId (url) {
      this.videoId = this.$youtube.getIdFromURL(url)
      // console.log(this.videoId)
    },
    playing () {
      // console.log("playing")
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
