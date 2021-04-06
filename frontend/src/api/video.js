import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'video';

async function getVideoById(video_id, success, fail) {
  await instance
    .get(`${COMMON}/detail/${video_id}`)
    .then(success)
    .catch(fail);
}

function getVideoList(page, size, success, fail) {
  instance
    .get(`${COMMON}/all`, { params: { page, size } })
    .then(success)
    .catch(fail);
}

export { getVideoById, getVideoList };
