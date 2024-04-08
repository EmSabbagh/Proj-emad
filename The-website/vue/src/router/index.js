import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path:'/eventDetail',
      name: 'eventDetail',
      component: () => import('../views/eventDeatil.vue')
    },{
      path:'/activeDetail',
      name: 'activeDetail',
      component: () => import('../views/activeDetail.vue')
    }
  ]
})

export default router
