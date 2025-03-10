import axios from "axios";
import {store} from "@/stores/store";
import {includes} from "core-js/internals/array-includes";

// function
export default {
  setUserData(token, username, id) {
    localStorage.setItem('username', username);
    localStorage.setItem('access_token', token);
    localStorage.setItem('id', id);

    store.commit('setUsername', username);
    store.commit('setToken', token);
    store.commit('setId', id);

    axios.interceptors.request.use(
      config => {
        // Если токен существует, добавляем заголовок Authorization
        if (token) {
          config.headers['Authorization'] = `Bearer ${token}`;
        } else {
          // Удаляем заголовок Authorization, если токена нет
          delete config.headers['Authorization'];
        }

        return config;
      },
      error => {
        return Promise.reject(error);
      }
    );
  },
  isAuthorized() {
    return store.getters.username !== null
      && store.getters.username !== '';
  },
  getUsername() {
    return store.getters.username;
  },
  unauthorize() {
    axios.interceptors.request.use(
      config => {
        config.headers['Authorization'] = `Bearer`;
        return config;
      },
      error => {
        return Promise.reject(error);
      })
    if (this.isAuthorized()) {
      this.setUserData(null, null, null)
    }
  },
  async authorizeUser(username, password) {
    await axios.post("/authenticate/login",
      {
        username: username,
        password: password
      }).then(response => {

      this.setUserData(response.data.token, username, response.data.id);
    }).catch(response => {
    });
    return store.getters.token;
  },
  register(username, password, email, personType = 'I') {
    axios.post("/authenticate/register",
      {
        username: username,
        password: password,
        email: email,
        personType: personType
      }).then(response => {

      this.setUserData(response.data.token, username, response.data.id);
    }).catch(response => {
    });
  },
  getUser(id) {
    return axios.get(`/user/get/${id}`);
  },
  updateUserInfo(id, user) {
    return axios.post(`/user/change/${id}`, {
      lastName: user.lastName,
      firstName: user.firstName,
      middleName: user.middleName,
      email: user.email,
      phoneNumber: user.phoneNumber
    });
  }
}
