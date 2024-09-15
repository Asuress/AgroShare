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
import {store} from "@/stores/store"

Axios.defaults.baseURL = "http://80.242.58.150:8001/api/"

const app = createApp(App)

app.config.globalProperties.$router = Router;
app.config.globalProperties.$username = '';
app.config.errorHandler = (err, vm, info) => {
  console.error('Error caught in global handler:', err);
  console.error('Component:', vm);
  console.error('Info:', info);

  // Вы можете выводить сообщение или показывать его пользователю
  alert('Произошла ошибка: ' + err.message);
};

app.use(store);
app.use(Router);

registerPlugins(app)
// app.component(Router)

app.mount('#app')
