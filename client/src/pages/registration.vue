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
              :rules="[RulesUtils.rules.required]"
            ></v-text-field>
            <v-text-field
              id="password"
              prepend-icon="lock"
              name="password"
              label="Password"
              type="password"
              v-model="user.password"
              :rules="[RulesUtils.rules.required]"
            ></v-text-field>
            <v-text-field
              id="email"
              prepend-icon="lock"
              name="email"
              label="Email"
              type="text"
              v-model="user.email"
              :rules="[this.RulesUtils.rules.required, RulesUtils.rules.email]"
            ></v-text-field>
            <v-radio-group v-model="user.type"
            >
              <v-radio id="user-type"
                       name="user-type"
                       value="I"
                       label="Физ. лицо"
              ></v-radio>
              <v-radio id="user-type"
                       name="user-type"
                       value="L"
                       label="Юр. лицо"
              ></v-radio>
            </v-radio-group>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="register">Register</v-btn>
        </v-card-actions>
      </v-card>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";
import {ru} from "vuetify/locale";
import RulesUtils from "@/utils/rules-utils"
import router from "@/router";
import UserHelper from "@/utils/user-helper";

export default {
  name: 'login',
  components: {
    RulesUtils
  },
  computed: {
    RulesUtils() {
      return RulesUtils
    },
    ru() {
      return ru
    }
  },
  props: {
    source: String,
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
        email: "",
        type: "I"
      }
    }
  },
  methods: {
    register() {
      console.log("register", this.user)
      UserHelper.register(this.user.username,
        this.user.password,
        this.user.email,
        this.user.type);
      // this.$router.go(-1);
    },
    required(v) {
      return !!v || 'Field is required'
    },
  }
};
</script>

<style></style>
