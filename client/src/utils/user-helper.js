import axios from "axios";
import {store} from "@/stores/store";

function setUserData(token, username, id) {
  console.log("setUserData begin", username, token)
  localStorage.setItem('username', username);
  localStorage.setItem('access_token', token);
  localStorage.setItem('id', id);

  store.commit('setUsername', username);
  store.commit('setToken', token);
  store.commit('setId', id);

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
    return store.getters.username !== null
      && store.getters.username !== '';
  },
  getUsername() {
    console.log("getUsername:", store.getters.username);
    return store.getters.username;
  },
  unauthorize() {
    if (this.isAuthorized()) {
      store.commit('setUsername', '');
      store.commit('setToken', '');
    }
  },
  async authorizeUser(username, password) {
    await axios.post("/authenticate/login",
      {
        username: username,
        password: password
      }).then(response => {
      console.log("authorizeUser: token", response.data.token)
      console.log("authorizeUser: id", response.data.id)

      setUserData(response.data.token, username, response.data.id);
    }).catch(response => {
      console.log("authorizeUser: error")
      console.log(response)
      console.log(response.data)
    });
    return store.getters.token;
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
      console.log("register: id", response.data.id)

      setUserData(response.data.token, username, response.data.id);
    }).catch(response => {
      console.log(response);
    });
    console.log("afret post");
  },
  getUser(id) {
    return axios.get(`/user/get/${id}`);
  }
}
