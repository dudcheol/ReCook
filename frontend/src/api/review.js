import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'review';

function getReviewById(reviewId, success, fail) {
  instance
    .get(`${COMMON}/detail/${reviewId}`)
    .then(success)
    .catch(fail);
}

function getAllReviews(page, size, success, fail) {
  instance
    .get(`${COMMON}/all`, { params: { page, size } })
    .then(success)
    .catch(fail);
}

function writeReview(recipeId, reviewContext, reviewImage, reviewRating, userId, success, fail) {
  instance
    .post(`${COMMON}/create`, { recipeId, reviewContext, reviewImage, reviewRating, userId })
    .then(success)
    .catch(fail);
}

function getReviewByUserName(userName, success, fail) {
  instance
    .get(`${COMMON}/user/${userName}`)
    .then(success)
    .catch(fail);
}

export { getReviewById, getAllReviews, writeReview, getReviewByUserName };
