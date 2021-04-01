import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'user';

function getUserInfoByName(userName, success, fail) {
  instance
    .get(`${COMMON}/mypage/${userName}`)
    .then(success)
    .catch(fail);
}

export { getUserInfoByName };
