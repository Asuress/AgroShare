import Axios from "axios";

const axios = Axios.create({
  // baseURL: 'http://80.242.58.150:8001/api/',
  baseURL: 'http://localhost:8080/',
});

export default axios
