import Axios from "axios";

const axios = Axios.create({
  baseURL: 'http://80.242.58.150:8001/api/',
});

export default axios
