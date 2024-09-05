/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import {registerPlugins} from '@/plugins'

// Components
import App from './App.vue';

// Composables
import {createApp} from 'vue'
import Router from "@/router";
import Axios from "axios";
import {store} from "./stores/app"
import * as colors from "vuetify/util/colors";

Axios.defaults.baseURL = "http://localhost:8080"

const app = createApp(App)

// app.config.globalProperties.$vuetify.theme = {
//   themes: {
//     light: {
//       primary: colors.purple,
//       secondary: colors.grey.darken1,
//       accent: colors.shades.black,
//       error: colors.red.accent3,
//       background: colors.indigo.lighten5, // Not automatically applied
//       // ...
//     },
//     dark: {
//       primary: colors.blue.lighten3,
//       background: colors.indigo.base, // If not using lighten/darken, use base to return hex
//       // ...
//     },
//   },
// }

app.config.globalProperties.$router = Router;
app.config.globalProperties.$username = '';
app.config.globalProperties.$store = store;
// app.config.globalProperties.$axios = Axios;

registerPlugins(app)
app.component(Router)

app.mount('#app')
