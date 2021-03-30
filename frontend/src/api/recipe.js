import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'recipe';

function getRecipeById(recipe_id, success, fail) {
  instance
    .get(`${COMMON}/show/${recipe_id}`)
    .then(success)
    .catch(fail);
}

function getRecipeNewList(success, fail) {
  instance
    .get(`${COMMON}/show/new/list`)
    .then(success)
    .catch(fail);
}

export { getRecipeById, getRecipeNewList };
