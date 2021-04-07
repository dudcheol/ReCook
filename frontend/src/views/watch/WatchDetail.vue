<template>
  <div class="d-flex flex-wrap align-center justify-center fill-height">
    <div>
      <div>
        <div class="d-flex align-center pa-3 white">
          <v-avatar size="36">
            <img :src="videoInfo.videoChannelImg" />
          </v-avatar>
          <span class="pl-2 sub-title-1">{{ videoInfo.videoChannel }}</span>
        </div>
      </div>
      <youtube
        :video-id="videoSubId"
        :player-width="windowWidth"
        @ready="ready"
        @playing="playing"
      />
      <div class="py-3 px-4 sub-title-1 white rounded-xl black--text ma-3 h6 font-weight-medium">
        {{ videoInfo.videoTitle }}
      </div>
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
