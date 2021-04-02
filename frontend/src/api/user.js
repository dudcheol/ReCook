import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'user';

function setAuthTokenToHeader(token) {
  instance.defaults.headers.common['authToken'] = token;
}

function removeAuthTokenToHeader() {
  instance.defaults.headers.common['authToken'] = undefined;
}

async function login(userInfo, success, fail) {
  await instance
    .post(`${COMMON}/login/google`, userInfo)
    .then(success)
    .catch(fail);
}

async function getUserInfoByAuthToken(success, fail) {
  await instance
    .get(`${COMMON}/mypage/Token`)
    .then(success)
    .catch(fail);
}

function getUserInfoByName(userName, success, fail) {
  instance
    .get(`${COMMON}/mypage/${userName}`)
    .then(success)
    .catch(fail);
}

function getLikeListByUserId(userId, page, size, success, fail) {
  instance
    .get(`${COMMON}/like/${userId}`, { params: { page, size } })
    .then(success)
    .catch(fail);
}

export {
  login,
  getUserInfoByName,
  getUserInfoByAuthToken,
  setAuthTokenToHeader,
  removeAuthTokenToHeader,
  getLikeListByUserId,
};
