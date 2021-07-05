import axios from 'axios';
import { API_BASE_URL, API_BIGDATA_URL } from '../../config';

function createInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return instance;
}

function createBigdataInstance() {
  const instance = axios.create({
    baseURL: API_BIGDATA_URL,
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return instance;
}

export { createInstance, createBigdataInstance };
