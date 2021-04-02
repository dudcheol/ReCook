import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'video';

// 여기에 코드 작성
function getVideoById(video_id, success, fail) {
    instance
      .get(`${COMMON}/detail/${video_id}`)
      .then(success)
      .catch(fail);
  }

export { getVideoById };
