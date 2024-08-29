/**
 * router/index.ts
 *
 * Automatic routes for `./src/pages/*.vue`
 */

// Composables
import { createRouter, createWebHistory } from 'vue-router/auto'
// import { setupLayouts } from 'virtual:generated-layouts'
// import { routes } from 'vue-router/auto-routes'
import Index from '@/pages/index.vue'
import Articles from '@/pages/articles.vue'
import Publications from "@/pages/publications/publications.vue"
import Login from "@/pages/login.vue"
import News from "@/pages/news/news.vue";
import Registration from "@/pages/registration.vue";
import AddPublication from "@/pages/publications/add-publication.vue";
import Publication from "@/pages/publications/publication.vue";
import Profile from "@/pages/profile.vue";


const routes = [
  {
    path: '/',
    name: 'index',
    component: Index
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile
  },
  {
    path: '/publications',
    name: 'publications',
    component: Publications
  },
  {
    path: '/publications/publication/:id',
    name: 'publication',
    component: Publication
  },
  {
    path: '/add-publication',
    name: 'add-publication',
    component: AddPublication
  },
  {
    path: '/articles',
    name: 'articles',
    component: Articles
  },
  {
    path: '/news',
    name: 'news',
    component: News
  },
  {
    path: '/registration',
    name: 'registration',
    component: Registration
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  // routes: setupLayouts(routes),
  routes
})

// Workaround for https://github.com/vitejs/vite/issues/11804
router.onError((err, to) => {
  if (err?.message?.includes?.('Failed to fetch dynamically imported module')) {
    if (!localStorage.getItem('vuetify:dynamic-reload')) {
      console.log('Reloading page to fix dynamic import error')
      localStorage.setItem('vuetify:dynamic-reload', 'true')
      location.assign(to.fullPath)
    } else {
      console.error('Dynamic import error, reloading page did not fix it', err)
    }
  } else {
    console.error(err)
  }
})

router.isReady().then(() => {
  localStorage.removeItem('vuetify:dynamic-reload')
})

export default router
