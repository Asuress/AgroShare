import Axios from "axios";

const axios = Axios.create({
  baseURL: 'https://agro-share.ru/api/',
  // baseURL: 'http://localhost:8080/',
});

export default axios
