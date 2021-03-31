import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'hashtag';

function getHashtagListByRecipeId(recipeId, success, fail) {
  instance
    .get(`${COMMON}/hashtagList/${recipeId}`)
    .then(success)
    .catch(fail);
}

export { getHashtagListByRecipeId };
