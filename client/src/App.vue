<template>
  <v-app>
    <app-header :username="username"
    />


    <v-main class="bg-grey-lighten-2"
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
      console.log("changeUsername");
      this.username = newUsername;
    }
  },
  mounted() {
    console.log(!!localStorage.getItem('access_token'));
    if (!!localStorage.getItem('access_token')) {
      this.$store.commit('setToken', localStorage.getItem('access_token'));
      this.$store.commit('setUsername', localStorage.getItem('username'));
      this.$store.commit('setId', localStorage.getItem('id'));

      console.log("44");
      axios.interceptors.request.use(
        config => {
          config.headers['Authorization'] = `Bearer ${localStorage.getItem('access_token')}`;
          return config;
        },
        error => {
          return Promise.reject(error);
        }
      );
      // this.username = ;
    } else {
      this.$store.commit('setToken', null);
      this.$store.commit('setUsername', null);
      this.$store.commit('setId', null);

      console.log("55", axios.interceptors.request);
    }
  }

}
</script>
