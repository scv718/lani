import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'candleView',
    component: () => import(/* webpackChunkName: "about" */ '../views/candleView.vue')
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/MapView.vue')
  },
  {
    path: '/add',
    name: 'add',
    component: () => import(/* webpackChunkName: "add" */ '../views/ManagerView.vue')
  },
  {
    path: '/candle',
    name: 'candle',
    component: () => import(/* webpackChunkName: "add" */ '../views/candleView.vue')
  },
  {
    path: '/main',
    name: 'main',
    component: () => import(/* webpackChunkName: "add" */ '../views/HomeView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
