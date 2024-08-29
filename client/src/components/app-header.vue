<template>
  <v-toolbar app :elevation="8" color="teal-darken-2" class="hidden-xs-and-down">
    <v-icon icon="appIcon"></v-icon>
    <v-toolbar-title
      style="cursor: pointer"
      @click="$router.push('/')">
      АгроShare
    </v-toolbar-title>

    <v-toolbar-items>
      <v-btn
        v-for="item in menuItems"
        :key="item.icon"
        :to="item.url"
        :title="item.title"
        flat
      >{{ item.title }}
      </v-btn>
    </v-toolbar-items>

    <v-spacer></v-spacer>

    <v-btn v-if="username === '' || username === null"
           router to="/login"
           text="Login"></v-btn>
    <v-btn v-if="username === '' || username === null"
           router to="/registration"
           text="Registration"></v-btn>
    <v-btn v-if="username !== null && username !== ''"
           text="Log out"
           @click="logout"
    ></v-btn>
    <v-icon v-if="isAuthorized"
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
    isAuthorized() {
      UserHelper.isAuthorized();
    }
  },
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
    username: null
  }),
  methods: {
    logout() {
      UserHelper.unauthorize();
      this.username = '';
    },
    profile() {
      this.$router.push("/profile");
    }
  },
  mounted() {
    this.username = UserHelper.getUsername();
    console.log("username", this.username);
  },
  watch: {
    username: function (val) {
      this.isAuthorized = val !== null && val !== '';
    }
  }
}

</script>

<style scoped lang="sass">

</style>
