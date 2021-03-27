import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'recipe';

function getRecipeNewList(success, fail) {
  instance
    .get(`${COMMON}/show/new/list`)
    .then(success)
    .catch(fail);
}

export { getRecipeNewList };
