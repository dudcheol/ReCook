import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'ingredients';

function getBigList(success, fail) {
  instance
    .get(`${COMMON}/getBigList`)
    .then(success)
    .catch(fail);
}

function getMiddleList(bigId, success, fail) {
  instance
    .get(`${COMMON}/getMiddleList/${bigId}`)
    .then(success)
    .catch(fail);
}

function getSmallList(midId, success, fail) {
  instance
    .get(`${COMMON}/getSmallList/${midId}`)
    .then(success)
    .catch(fail);
}

export { getBigList, getMiddleList, getSmallList };
