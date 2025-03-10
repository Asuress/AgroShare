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
import {createVuetify} from "vuetify";
import {components, directives} from "vuetify/dist/vuetify";
import {aliases, mdi} from "vuetify/iconsets/mdi";

// Axios.defaults.baseURL = "https://80.242.58.150:8001/api/"
Axios.defaults.baseURL = "https://agro-share.ru/api/"
// Axios.defaults.baseURL = "http://localhost:8080/"

// Создание Vuetify экземпляра с настройками темы
const vuetify = createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'light', // Установите тему по умолчанию
    themes: {
      light: {
        colors: {
          primary: '#3f51b5', // Основной цвет
          secondary: '#ff5722', // Вторичный цвет
          background: '#f5f5f5', // Цвет фона
        },
      },
      dark: {
        colors: {
          primary: '#1e88e5',
          background: '#ff5722',
        },
      },
    },
  },
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: {
      mdi,
    },
  },
});

const app = createApp(App)

// app.config.
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
app.use(vuetify);

registerPlugins(app)
// app.component(Router)

app.mount('#app')
