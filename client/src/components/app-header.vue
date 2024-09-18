<template>
  <!--  teal-darken-2-->
  <v-toolbar app
             :elevation="8"
             class="hidden-xs-and-down my-custom-background-header">
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

    <v-btn v-if="innerUsername === '' || innerUsername === null || innerUsername === undefined"
           router to="/login"
           @click="login"
           text="Login"></v-btn>
    <v-btn v-if="innerUsername === '' || innerUsername === null || innerUsername === undefined"
           router to="/registration"
           @click="registration"
           text="Registration"
    ></v-btn>
    <v-btn v-if="innerUsername !== '' && innerUsername !== null && innerUsername !== undefined"
           text="Log out"
           @click="logout"
    ></v-btn>
    <v-icon v-if="innerUsername !== '' && innerUsername !== null && innerUsername !== undefined"
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
  computed: {
    innerUsername() {
      console.log(this.$store.getters.username !== '' && this.$store.getters.username !== null && this.$store.getters.username !== undefined)
      console.log('computed username in app-header', this.$store.getters.username);
      return this.$store.getters.username;
    },
    isAuthorized() {
      let username = this.$store.state.username;
      console.log('computed isAuthorized in app-header', username !== null && username !== '' && username !== undefined);
      return this.$store.state.username !== null && this.$store.state.username !== '' && this.$store.state.username !== undefined;
    }
  },
  watch: {
    innerUsername(newUsername, oldUsername) {
      console.log(`We have ${newUsername} fruits now, yay!`)
    },
    isAuthorized(newValue, oldValue) {
      console.log(`is authorized: ${newValue}`);
    },
  },
  mixins: [
    UserHelper
  ],
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
    // isAuthorized: false,
    // innerUsername: null
  }),
  methods: {
    login() {
      console.log("before push to login")
      this.$router.push("/login");
      // this.innerUsername = UserHelper.getUsername();
    },
    registration() {
      this.$router.push("/registration");
      // this.innerUsername = UserHelper.getUsername();
    },
    logout() {
      UserHelper.unauthorize();
      // this.innerUsername = '';
    },
    profile() {
      console.log("id:", this.$store.getters.id);
      this.$router.push(`/profile/${this.$store.getters.id}`);
    }
  },
  mounted() {
    console.log("mounted");
    // this.innerUsername = UserHelper.getUsername();
  }
}

</script>

<style scoped>
.my-custom-background-header {
  background-color: #7B382E; /* ваш кастомный цвет */
}
</style>
