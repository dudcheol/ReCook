<template>
  <div class="d-flex flex-column pb-16 fill-height">
    <div class="flex-grow-0">
      <v-img
        v-if="selectedFile"
        :src="selectedFileURL"
        aspect-ratio="1"
        :loading="isSelecting"
        class="grey lighten-3"
      >
        <div class="text-right pa-3">
          <v-btn fab color="warning" dark small class="mr-1" @click="onButtonClick">
            <v-icon>mdi-camera</v-icon>
          </v-btn>
          <v-btn fab color="error" dark small @click="onFileRemoved"
            ><v-icon>mdi-trash-can</v-icon></v-btn
          >
        </div>
      </v-img>
      <v-btn
        v-else
        block
        tile
        depressed
        color="grey lighten-4"
        class="py-13"
        :loading="isSelecting"
        @click="onButtonClick"
      >
        <v-icon large color="grey lighten-1">mdi-camera</v-icon>
      </v-btn>
      <input ref="uploader" class="d-none" type="file" accept="image/*" @change="onFileChanged" />
    </div>
    <div ref="textarea" class="flex-grow-1">
      <v-textarea
        v-model="content"
        flat
        autofocus
        solo
        label="내용을 입력하세요"
        full-width
        :height="textareaHeight"
        style="font-size:1.3em"
      ></v-textarea>
    </div>
    <div class="footer">
      <div
        class="grey lighten-4 py-2 ma-4 d-flex flex-column align-center justify-center rounded-lg elevation-24"
      >
        <span class="caption-1">레시피를 <strong>평가</strong>해주세요</span>
        <v-rating
          v-model="rating"
          dense
          empty-icon="mdi-star-outline"
          full-icon="mdi-star"
          half-icon="mdi-star-half"
          half-increments
          length="5"
          size="24"
          color="yellow"
          background-color="grey lighten-2"
        ></v-rating>
      </div>
      <v-btn
        block
        color="dahong"
        height="56"
        tile
        class="white--text font-weight-black subtitle-1 pt-1"
        :disabled="btnActive || loading"
        depressed
        @click="onReviewWrited"
      >
        작성 완료
      </v-btn>
      <v-overlay :value="loading">
        <loading-cheers :height="120" :width="120" class="white rounded-circle"></loading-cheers>
      </v-overlay>
    </div>
  </div>
</template>

<script>
import { writeReview } from '@/api/review';
import LoadingCheers from '@/components/common/LoadingCheers.vue';
export default {
  components: {
    LoadingCheers,
  },
  props: {},
  data() {
    return {
      reviewId: 0,
      content: '',
      textareaHeight: 0,
      rating: 0,
      selectedFile: null,
      isSelecting: false,
      loading: false,
    };
  },
  computed: {
    btnActive() {
      return !(this.content.trim() && this.selectedFile && this.rating > 0);
    },
    selectedFileURL() {
      return URL.createObjectURL(this.selectedFile);
    },
  },
  watch: {
    $route: {
      immediate: true,
      handler(value) {
        this.reviewId = value.params['recipe_id'];
      },
    },
  },
  methods: {
    onButtonClick() {
      this.isSelecting = true;
      window.addEventListener(
        'focus',
        () => {
          this.isSelecting = false;
        },
        { once: true }
      );

      this.$refs.uploader.click();
    },
    onFileChanged(e) {
      this.selectedFile = e.target.files[0] || this.selectedFile;
    },
    onFileRemoved() {
      this.selectedFile = '';
    },
    onReviewWrited() {
      this.loading = true;

      let formData = new FormData();

      formData.append('recipeId', this.$route.params['recipe_id']);
      formData.append('reviewContext', this.content);
      formData.append('reviewImage', this.selectedFile);
      formData.append('reviewRating', this.rating);
      formData.append('userId', this.$store.state.user.user.userId);

      writeReview(
        formData,
        () => {
          this.loading = false;
          this.$router.go(-1);
        },
        () => {
          this.loading = false;
        }
      );
    },
  },
  mounted() {
    this.textareaHeight = this.$refs.textarea.clientHeight - 80;
  },
};
</script>

<style scoped>
.footer {
  position: fixed;
  bottom: 0px;
  width: 100%;
}
</style>
