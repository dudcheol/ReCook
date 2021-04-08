import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'review';

function getReviewById(reviewId, success, fail) {
  instance
    .get(`${COMMON}/detail/${reviewId}`)
    .then(success)
    .catch(fail);
}

async function getAllReviews(page, size, success, fail) {
  await instance
    .get(`${COMMON}/all/image`, { params: { page, size } })
    .then(success)
    .catch(fail);
}

function writeReview(formData, success, fail) {
  instance
    .post(`${COMMON}/create`, formData)
    .then(success)
    .catch(fail);
}

function getReviewByUserName(userName, success, fail) {
  instance
    .get(`${COMMON}/user/${userName}`)
    .then(success)
    .catch(fail);
}

function getReviewByRecipeId(recipeId, success, fail) {
  instance
    .get(`${COMMON}/recipe/${recipeId}`)
    .then(success)
    .catch(fail);
}

export { getReviewById, getAllReviews, writeReview, getReviewByUserName, getReviewByRecipeId };
