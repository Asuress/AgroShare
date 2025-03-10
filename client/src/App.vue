<template>
  <v-app>
    <app-header :username="username"
    />


    <v-main class="my-custom-background"
            @usernameChanged="changeUsername"
    >
      <router-view />
    </v-main>

    <AppFooter/>
  </v-app>
</template>

<script>

import AppFooter from "@/components/AppFooter.vue";
import AppHeader from "@/components/app-header.vue";
import axios from "axios";
import UserHelper from "@/utils/user-helper";

export default {
  name: 'App',
  components: {
    AppHeader,
    AppFooter
  },
  data() {
    return {
      username: '',
      notifications: null
    }
  },
  methods: {
    changeUsername(newUsername) {
      this.username = newUsername;
    }
  },
  mounted() {
    if (localStorage.getItem('access_token') !== 'null'
      && localStorage.getItem('access_token') !== null) {
      this.$store.commit('setToken', localStorage.getItem('access_token'));
      this.$store.commit('setUsername', localStorage.getItem('username'));
      this.$store.commit('setId', localStorage.getItem('id'));

      UserHelper.setUserData(localStorage.getItem('access_token'), localStorage.getItem('username'), localStorage.getItem('id'))
      // axios.interceptors.request.use(
      //   config => {
      //     config.headers['Authorization'] = `Bearer ${localStorage.getItem('access_token')}`;
      //     return config;
      //   },
      //   error => {
      //     return Promise.reject(error);
      //   }
      // );
      // this.username = ;
    } else {
      this.$store.commit('setToken', null);
      this.$store.commit('setUsername', null);
      this.$store.commit('setId', null);

    }
  }

}
</script>

<style>
.my-custom-background {
  background-color: #D8DCDF; /* ваш кастомный цвет */
}
.my-custom-background-button-primary {
  background-color: #2E7D32;
}
.my-custom-background-button {
  background-color: #2E7D32;
  color: whitesmoke;
}
v-btn {
  background-color: #ffff00;
}
</style>
