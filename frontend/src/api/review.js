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

export { getReviewById, getAllReviews };
