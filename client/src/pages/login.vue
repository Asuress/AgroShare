<template>
  <v-container flex app>
    <v-layout>
      <v-card justify-center
              class="elevation-12 flex-0-0-100 black--text my-custom-background"
              color="grey-lighten-3">
        <v-toolbar class="my-custom-background-button-primary">
          <v-toolbar-title>Форма авторизации</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-text-field
              prepend-icon="person"
              name="login"
              label="Имя пользователя"
              type="text"
              v-model="user.username"
              :rules="[required]"
            ></v-text-field>
            <v-text-field
              id="password"
              prepend-icon="lock"
              name="password"
              label="Пароль"
              type="password"
              v-model="user.password"
              :rules="[required]"
            ></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="my-custom-background-button"
                 @click="login"
          >
            Login
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-layout>
  </v-container>
</template>

<script>
import UserHelper from "@/utils/user-helper";

export default {
  name: 'login',
  components: {
    UserHelper
  },
  props: {
    source: String,
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      }
    }
  },
  methods: {
    async login() {
      await UserHelper.authorizeUser(this.user.username, this.user.password);
      this.user.username = UserHelper.getUsername();
      console.log("this.user.username", this.user.username);
      this.$emit('usernameChanged', this.user.username);
      this.$router.go(-1);
    },
    required(v) {
      return !!v || 'Field is required'
    },
    changeUsername() {
    }
  },
  mounted() {

  }
};
</script>

<style scoped>

</style>
