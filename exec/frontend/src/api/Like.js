import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'recipe';

function checkLike(recipeId, userId,success, fail) {
  instance
    .post(`${COMMON}/check/like`, { recipeId,userId })
    .then(success)
    .catch(fail);
}

function likePush(recipeId, userId,success, fail) {
  instance
    .post(`${COMMON}/like`,{ recipeId,userId })
    .then(success)
    .catch(fail);
}

export {checkLike,likePush};