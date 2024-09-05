<template>
  <!--  teal-darken-2-->
  <v-toolbar app
             :elevation="8"

             color="light-blue-darken-3"
             class="hidden-xs-and-down">
    <v-icon icon="appIcon"></v-icon>
    <v-toolbar-title
      style="cursor: pointer"
      @click="$router.push('/')">
      АгроShare
    </v-toolbar-title>

    <v-toolbar-items>
      <v-btn v-for="item in menuItems"
             :key="item.icon"
             :to="item.url"
             :title="item.title"
             flat
      >{{ item.title }}
      </v-btn>
    </v-toolbar-items>

    <v-spacer></v-spacer>

    <v-btn v-if="innerUsername === '' || innerUsername === null"
           router to="/login"
           @click="login"
           text="Login"></v-btn>
    <v-btn v-if="innerUsername === '' || innerUsername === null"
           router to="/registration"
           @click="registration"
           text="Registration"></v-btn>
    <v-btn v-if="innerUsername !== null && innerUsername !== ''"
           text="Log out"
           @click="logout"
    ></v-btn>
    <v-icon v-if="innerUsername !== null && innerUsername !== ''"
            right
            icon="mdi-account"
            size="x-large"
            class="ma-4"
            @click="profile"
    ></v-icon>

  </v-toolbar>
</template>

<script>
import UserHelper from "@/utils/user-helper";

export default {
  props: {
    username: '',
  },
  data: () => ({
    menuItems: [
      {
        title: "Главная",
        url: "/",
        icon: "appIcon"
      },
      {
        title: "Объявления",
        url: "/publications",
        icon: "publicationsIcon"
      },
      {
        title: "Новости",
        url: "/news",
        icon: "newsIcon"
      },
    ],
    isAuthorized: false,
    innerUsername: null
  }),
  methods: {
    login() {
      this.$router.push("/login");
      console.log("after push to login")
      this.innerUsername = UserHelper.getUsername();
    },
    registration() {
      this.$router.push("/registration");
      this.innerUsername = UserHelper.getUsername();
    },
    logout() {
      UserHelper.unauthorize();
      this.innerUsername = '';
    },
    profile() {
      this.$router.push("/profile");
    }
  },
  mounted() {
    this.innerUsername = UserHelper.getUsername();
    console.log("username", this.innerUsername);
    console.log("store", this.$store);
  }
}

</script>

<style scoped lang="sass">

</style>
