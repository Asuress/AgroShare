/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import { registerPlugins } from '@/plugins'

// Components
import App from './App.vue';

// Composables
import { createApp } from 'vue'
import Router from "@/router";
import Axios from "axios";

Axios.defaults.baseURL = "http://localhost:8080"

const app = createApp(App)

app.config.globalProperties.$router = Router;
app.config.globalProperties.$username = '';
// app.config.globalProperties.$axios = Axios;

registerPlugins(app)
app.component(Router)

app.mount('#app')
