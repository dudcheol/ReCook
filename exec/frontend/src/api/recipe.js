import { createInstance, createBigdataInstance } from './index.js';

const instance = createInstance();
const bigdataInstance = createBigdataInstance();
const COMMON = 'recipe';

async function getRecipeById(recipe_id, success, fail) {
  await instance
    .get(`${COMMON}/show/main/${recipe_id}`)
    .then(success)
    .catch(fail);
}

function getRecipeNewList(success, fail) {
  instance
    .get(`${COMMON}/show/new/list`)
    .then(success)
    .catch(fail);
}

function getRecipeNewListAll(page, size, success, fail) {
  instance
    .get(`${COMMON}/show/new/list/all`, { params: { page, size } })
    .then(success)
    .catch(fail);
}

function getRecipeHotList(success, fail) {
  instance
    .get(`${COMMON}/show/hot/list`)
    .then(success)
    .catch(fail);
}

function getRecipeHotListAll(page, size, success, fail) {
  instance
    .get(`${COMMON}/show/hot/list/all`, { params: { page, size } })
    .then(success)
    .catch(fail);
}

function getRecommRecipeListByUser(userId, success, fail) {
  bigdataInstance
    .get(`bigdata/similar/recommend/${userId}`)
    .then(success)
    .catch(fail);
}

function getRecommRecipeByRecipeTitle(recipeTitle, userId, success, fail) {
  bigdataInstance
    .get(`bigdata/similar/recipe/${recipeTitle}/${userId}`)
    .then(success)
    .catch(fail);
}

function getRecommRecipeByIngredients(ingredientList, userId, allergy, success, fail) {
  instance
    .post(`${COMMON}/select/${allergy ? 'allergy' : 'ingredients'}`, {
      ingredientList,
      userId,
    })
    .then(success)
    .catch(fail);
}

function getIngredientsInRecipe(recipeId, success, fail) {
  instance
    .get(`${COMMON}/ingredients/${recipeId}`)
    .then(success)
    .catch(fail);
}

function getRecipeByTitle(title, page, size, success, fail) {
  instance
    .get(`${COMMON}/recipe/title/${title}`, { params: { page, size } })
    .then(success)
    .catch(fail);
}

export {
  getRecipeById,
  getRecipeNewList,
  getRecipeNewListAll,
  getRecipeHotList,
  getRecipeHotListAll,
  getRecommRecipeListByUser,
  getRecommRecipeByRecipeTitle,
  getRecommRecipeByIngredients,
  getIngredientsInRecipe,
  getRecipeByTitle,
};
