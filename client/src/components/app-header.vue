<template>
  <!--  teal-darken-2-->
  <v-toolbar app
             :elevation="8"
             class="hidden-xs-and-down my-custom-background-header">
    <!-- Изображение слева -->
    <v-row class="d-flex align-start">
      <v-col class="d-flex align-start">
        <v-img
          src="@/assets/AgroShareLogo.png"
          class="logo"
          contain
          height="90"
          @click="$router.push('/')"
          style="cursor: pointer; margin-left: 16px;"
        ></v-img>
      </v-col>
    </v-row>
    <!-- Контейнер для изображения и заголовка -->
<!--    <v-toolbar-title class="d-flex"-->
<!--                     @click="$router.push('/')"-->
<!--                     style="cursor: pointer; margin-left: 16px;"-->
<!--    >-->


<!--      &lt;!&ndash; Заголовок &ndash;&gt;-->
<!--      &lt;!&ndash;      <span&ndash;&gt;-->
<!--      &lt;!&ndash;        class="toolbar-title"&ndash;&gt;-->
<!--      &lt;!&ndash;      >&ndash;&gt;-->
<!--      &lt;!&ndash;        АгроShare&ndash;&gt;-->
<!--      &lt;!&ndash;        </span>&ndash;&gt;-->
<!--    </v-toolbar-title>-->

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
           text="Вход"></v-btn>
    <v-btn v-if="innerUsername === '' || innerUsername === null || innerUsername === undefined"
           router to="/registration"
           @click="registration"
           text="Регистрация"
    ></v-btn>
    <v-btn v-if="innerUsername !== '' && innerUsername !== null && innerUsername !== undefined"
           text="Выход"
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
      return this.$store.getters.username;
    },
    isAuthorized() {
      let username = this.$store.state.username;
      return this.$store.state.username !== null && this.$store.state.username !== '' && this.$store.state.username !== undefined;
    }
  },
  watch: {
    innerUsername(newUsername, oldUsername) {
    },
    isAuthorized(newValue, oldValue) {
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
      this.$router.push(`/profile/another-profile/${this.$store.getters.id}`);
    }
  },
  mounted() {
    // this.innerUsername = UserHelper.getUsername();
  }
}

</script>
<!--background-color: #7B382E;-->
<style scoped>
.my-custom-background-header {
  /* ваш кастомный цвет */
  background-color: #43A047;
}

.logo {
  height: 40px; /* Установите нужную высоту для изображения */
  margin-right: 16px; /* Отступ справа от изображения */
}

.toolbar-title {
  line-height: 40px; /* Выравнивание текста по вертикали с изображением */
}
</style>
