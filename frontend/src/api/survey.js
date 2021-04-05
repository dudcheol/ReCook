import { createInstance } from './index.js';

const instance = createInstance();
const COMMON = 'survey';

function getFoodSurveyList(success, fail) {
  instance
    .get(`${COMMON}/food`)
    .then(success)
    .catch(fail);
}

function getAllergySurveyList(success, fail) {
  instance
    .get(`${COMMON}/allergy`)
    .then(success)
    .catch(fail);
}

async function saveSurveyResult(userId, foodList, allergyList, success, fail) {
  await instance
    .post(`${COMMON}/save/${userId}`, { foodList, allergyList })
    .then(success)
    .catch(fail);
}

export { getFoodSurveyList, getAllergySurveyList, saveSurveyResult };
