<template>
  <div class="d-flex flex-wrap align-center justify-center fill-height">
    <div>
      <div>
        <div class="d-flex align-center pa-3">
          <v-avatar size="48">
            <img :src="videoInfo.videoChannelImg" />
          </v-avatar>
          <span class="pl-2 h6">{{ videoInfo.videoChannel }}</span>
        </div>
      </div>
      <youtube
        :video-id="videoSubId"
        :player-width="windowWidth"
        @ready="ready"
        @playing="playing"
      />
      <div class="pa-3 subtitle-1">{{ videoInfo.videoTitle }}</div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import Vue from 'vue';
import VueYouTubeEmbed from 'vue-youtube-embed';

Vue.use(VueYouTubeEmbed);

export default {
  components: {},
  props: {},
  data() {
    return {
      videoId: '',
      windowWidth: 375,
    };
  },
  computed: {
    ...mapState({
      videoInfo: (state) => state.video.videoInfo,
    }),
    videoSubId() {
      return this.videoId;
    },
  },
  watch: {
    $route: {
      immediate: true,
      async handler(value) {
        await this.addVideoById(value.params['video_id']);
        this.getVideoId(this.videoInfo.videoUrl);
      },
    },
  },
  methods: {
    ...mapActions(['addVideoById']),

    ready(event) {
      this.player = event.target;
    },
    getVideoId(url) {
      this.videoId = this.$youtube.getIdFromURL(url);
    },
    playing() {},
    stop() {
      this.player.stopVideo();
    },
    pause() {
      this.player.pauseVideo();
    },
  },
  mounted() {
    this.windowWidth = window.innerWidth;
  },
};
</script>

<style scoped></style>
