<template>
  <v-container flex app>
    <v-layout>
      <v-card justify-center
              class="elevation-12 flex-0-0-100 black--text"
              color="grey-lighten-3">
        <v-toolbar dark color="teal-darken-2">
          <v-toolbar-title>Login form</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-text-field
              prepend-icon="person"
              name="login"
              label="Login"
              type="text"
              v-model="user.username"
              :rules="[required]"
            ></v-text-field>
            <v-text-field
              id="password"
              prepend-icon="lock"
              name="password"
              label="Password"
              type="password"
              v-model="user.password"
              :rules="[required]"
            ></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary"
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
    login() {
      UserHelper.authorizeUser(this.user.username, this.user.password);
    },
    required(v) {
      return !!v || 'Field is required'
    },
  },
  mounted() {

  }
};
</script>

<style></style>
