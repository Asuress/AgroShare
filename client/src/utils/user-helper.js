// import router from "@/router";
import axios from "axios";

function setUserData(data, username) {
  console.log("setUserData begin")
  localStorage.setItem('username', username);
  localStorage.setItem('access_token', data.token);
  axios.interceptors.request.use(
    config => {
      config.headers['Authorization'] = `Bearer ${localStorage.getItem('access_token')}`;
      return config;
    },
    error => {
      console.log("setUserData error")
      return Promise.reject(error);
    }
  );
}

export default {
  isAuthorized() {
    console.log("isAuthorized");
    return localStorage.getItem('username') !== null
      && localStorage.getItem('username') !== '';
  },
  getUsername() {
    return localStorage.getItem('username');
  },
  unauthorize() {
    if (this.isAuthorized()) {
      localStorage.setItem('username', "");
      localStorage.setItem('access_token', "");
    }
  },
  async authorizeUser(username, password) {
    await axios.post("/authenticate/login",
      {
        username: username,
        password: password
      }).then(response => {
      console.log("authorizeUser: token", response.data.token)

      setUserData(response.data, username);
    }).catch(response => {
      console.log("authorizeUser: error")
      console.log(response)
      console.log(response.data)
    });
    return localStorage.getItem('access_token');
  },
  register(username, password, email, personType = 'I') {
    console.log("register userhelper");
    axios.post("/authenticate/register",
      {
        username: username,
        password: password,
        email: email,
        personType: personType
      }).then(response => {
        console.log(response);
      setUserData(response.data, username);
    }).catch(response => {
      console.log(response);
    });
    console.log("afret post");
  }
}
